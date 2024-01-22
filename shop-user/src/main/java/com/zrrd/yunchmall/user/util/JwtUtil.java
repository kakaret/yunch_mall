package com.zrrd.yunchmall.user.util;

import com.zrrd.yunchmall.pojo.UserTmp;
import com.zrrd.yunchmall.user.entity.Admin;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * 声明一个JWT工具类
 * 1. 能够生成Token令牌
 * 2. 能够解析Token令牌（解析成功则token有效，解析失败则token无效）
 */
@SuppressWarnings("all")
public class JwtUtil {
    private static String salt = "0103";

    /**
     * 生成token令牌的方法，需要设置：令牌ID，签发人，主题，签发时间，有效时间
     * Claim需要包括：用户id，用户名...
     * 加密算法：SignatureAlgorithm.HS256
     * @param exp 令牌的有效时间，单位是毫秒
     * @param userInfo 用户信息
     * @return JWT令牌
     */
    public static String create(long exp, UserTmp userInfo) {
        JwtBuilder builder = Jwts.builder();
        builder.setId(UUID.randomUUID().toString());//设置id
        builder.setIssuer("ZRYC");//签发人
        builder.setIssuedAt(new Date());//签发时间
        builder.setExpiration(new Date(System.currentTimeMillis() + exp));//过期时间
        builder.setSubject("yunch_mall");//设置主题
        builder.claim("userId", userInfo.getUid());
        builder.claim("username", userInfo.getUsername());
        builder.signWith(SignatureAlgorithm.HS256, salt);//设置加密算法和盐（秘钥）
        //生成token并返回
        return "Bearer " + builder.compact();
    }


    public static String create(long exp, Admin userInfo) {
        JwtBuilder builder = Jwts.builder();
        builder.setId(UUID.randomUUID().toString());//设置id
        builder.setIssuer("ZRYC");//签发人
        builder.setIssuedAt(new Date());//签发时间
        builder.setExpiration(new Date(System.currentTimeMillis() + exp));//过期时间
        builder.setSubject("yunch_mall");//设置主题
        builder.claim("userId", userInfo.getId());
        builder.claim("username", userInfo.getUsername());
        builder.signWith(SignatureAlgorithm.HS256, salt);//设置加密算法和盐（秘钥）
        //生成token并返回
        return "Bearer " + builder.compact();
    }
    /**
     * 对给定的token进行解析，如果成功则从token中取出用户信息封装返回，如果解析出错则返回null
     * @param token
     * @return
     */
    public static UserTmp parse(String token) {
        try {
            Claims claims = Jwts.parser()
                    //设置盐（秘钥）
                    .setSigningKey(salt)
                    .parseClaimsJws(token).getBody();
            UserTmp userTmp = new UserTmp();
            userTmp.setUid(claims.get("userId", Integer.class));
            userTmp.setUsername(claims.get("username", String.class));
            return userTmp;
        } catch (Exception ex) {
            return null;
        }
    }

    public static Admin parseAdminToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    //设置盐（秘钥）
                    .setSigningKey(salt)
                    .parseClaimsJws(token).getBody();
            Admin admin = new Admin();
            admin.setId(claims.get("userId", Long.class));
            admin.setUsername(claims.get("username", String.class));
            return admin;
        } catch (Exception ex) {
            return null;
        }
    }
}
