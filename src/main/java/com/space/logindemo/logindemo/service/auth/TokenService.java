package com.space.logindemo.logindemo.service.auth;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.space.logindemo.logindemo.common.RedisCache;
import com.space.logindemo.logindemo.constants.CacheConstants;
import com.space.logindemo.logindemo.constants.Constants;
import com.space.logindemo.logindemo.model.login.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: leoyin
 * @Description:
 * @Date 2024/10/19 13:28
 */
@Component
public class TokenService {
    // 获取配置文件中的密钥参数
    @Value("${token.secret}")
    String secret;
    @Value("${token.expireTime}")
    Integer expireTime;
    @Value("${token.header}")
    String header;

    protected static final long MILLIS_SECOND = 1000; // 1s
    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND; // 1min
    protected static final long VERIFY_TIME = 20 * MILLIS_MINUTE; // 20min 续签
    @Resource
    private RedisCache redisCache;

    /**
     * 创建 token
     * @param loginUser
     * @return
     */
    public String createToken(LoginUser loginUser) {
        String tokenUUID = IdUtil.randomUUID();
        loginUser.setToken(tokenUUID);
        // 刷新 token
        refreshToken(loginUser);
        Map<String, Object> claim = new HashMap<>();
        claim.put(Constants.LOGIN_USER_KEY, tokenUUID);
        return Jwts.builder()
                .setClaims(claim)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 刷新 token
     * @param loginUser
     */
    private void refreshToken(LoginUser loginUser) {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(System.currentTimeMillis()  + expireTime * MILLIS_MINUTE);
        String tokenKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(tokenKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 获取redis保存的完整的 token
     * @param token
     * @return
     */
    private String getTokenKey(String token) {
        return CacheConstants.LOGIN_TOKEN_KEY + token;
    }

    /**
     * 删除 redis 保存的 token，用户退出时调用
     * @param token
     */
    public void deleteLoginUser(String token) {
        if (StrUtil.isNotBlank(token)) {
            String tokenKey = getTokenKey(token);
            redisCache.deleteObject(tokenKey);
        }
    }

    /**
     * 续签 token，用户获得信息时，如果时间不足，续签到30分
     * @param loginUser
     */
    public void verifyToken(LoginUser loginUser) {
        Long currentTime = System.currentTimeMillis();
        Long expireTime = loginUser.getExpireTime();
        if (expireTime - currentTime < VERIFY_TIME) {
            refreshToken(loginUser);
        }
    }

    /**
     * 获取登录对象
     * 根据登录请求中的 token，返回 redis 保存的用户
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求中的 token
        String token = getToken(request);
        if (StrUtil.isNotBlank(token)) {
            // 解析获得载荷信息
            Claims claims = parseToken(token);
            // 获取载荷中的 uuid
            String uuid = (String)claims.get(Constants.LOGIN_USER_KEY);
            // 获得完整 redis 保存形式 key:value
            String tokenKey = getTokenKey(uuid);
            // 获取登录对象
            LoginUser user = redisCache.getCacheObject(tokenKey);
            return user;
        }
        return null;
    }

    /**
     * 解析 token
     * @param token
     * @return
     */
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * 从请求中获取 token,前端发送的的形式为
     *   Authorization: Bearer token
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StrUtil.isNotBlank(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, StrUtil.EMPTY);
        }
        return token;
    }
}
