package com.zrrd.yunchmall.user.util;

import com.zrrd.yunchmall.pojo.UserTmp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

/**
 * 生命一个JWT工具类
 * 1.能够生成Token令牌
 * 2.能偶解析Token令牌（解析成功Token有效 解析失败Token无效）
 */

public class JwtUtil {
    private static String salt = "0103";

    /**
     * 用来生成Token令牌的方法 需要设置：令牌Id，签发人，主题，签发时间，有效时间
     * Claim需要包括用户Id 用户名...
     * 加密算法：SignatureAlgorithm.HS256
     *
     * @param exp      有效时间
     * @param userInfo 用户信息
     * @return Token令牌
     */
    public static String create(long exp, UserTmp userInfo) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setId(UUID.randomUUID().toString()) //设置Id
                .setIssuer("ZRYC") //签发人
                .setIssuedAt(new Date()) //签发人
                .setExpiration(new Date(System.currentTimeMillis() + exp)) //签发时间
                .setSubject("yunch_mall") //设置主题
                .signWith(SignatureAlgorithm.HS256, salt)//设置加密算法
                .claim("userId", userInfo.getUid())
                .claim("username", userInfo.getUsername());
        return "Bearer " + jwtBuilder.compact(); //生成token并返回
    }

    /**
     * logics
     * 对给定的token进行解析 如果成功则从token中取出用户信息封装返回 如果解析出错 则返回null
     *
     * @param token token令牌
     * @return 用户信息
     */
    public static UserTmp parse(String token) {

        try {
            Claims claims = Jwts.parser().setSigningKey(salt)
                    .parseClaimsJws(token).getBody();
            UserTmp userTmp = new UserTmp();
            userTmp.setUid(claims.get("userId", Integer.class));
            userTmp.setUsername(claims.get("username", String.class));
            return userTmp;
        } catch (Exception e) {
            return null;
        }
    }
}
