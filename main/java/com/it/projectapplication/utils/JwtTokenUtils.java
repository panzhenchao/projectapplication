package com.it.projectapplication.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenUtils {
    public static final String TOKEN_HEADER= "Authorization";
    public static final String TOKEN_PREFIX= "Bearer ";
    private static final String SECRET= "jwtsecretdom";
    private static final String ISS= "echisan";
    //过期时间3600秒
    private static final long EXPIRATION = 3600L;
    //记住密码 过期时间 7天
    private static final long EXPIRATION_REMEMBER = 604800L;
    public static String createrToken(String username,boolean isRememberMe){
        long expiration =isRememberMe? EXPIRATION_REMEMBER:EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.ES512,SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration +1000))
                .compact();
    }
    public static String getUsername(String token){
        return getTokenBoby(token).getSubject();
    }
    public static boolean isExPoration(String token){
        return getTokenBoby(token).getExpiration().before(new Date());
    }public static Claims getTokenBoby(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
