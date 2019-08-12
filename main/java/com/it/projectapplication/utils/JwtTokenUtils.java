package com.it.projectapplication.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JwtTokenUtils {
    public static final String TOKEN_HEADER= "Authorization";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String TOKEN_COOKIE_PREFIX="Bearer+";
    private static final String SECRET= "jwtsecretdom";
    private static final String ISS= "echisan";
    //过期时间3600秒
    private static final long EXPIRATION = 3600000L;
    //记住密码 过期时间 7天
    private static final long EXPIRATION_REMEMBER = 604800000L;
    private static final String ROLE_CLAIMS= "permission";
    public static String createrToken(String username, List permissionList, boolean isRememberMe){
        long expiration =isRememberMe? EXPIRATION_REMEMBER:EXPIRATION;
        HashMap<String,Object> map=new HashMap<>();
        map.put(ROLE_CLAIMS,permissionList);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration +1000))
                .compact();
    }
    public static String getUsername(String token){
        return getTokenBoby(token).getSubject();
    }
    public static List getUserPermission(String token){return (List) getTokenBoby(token).get(ROLE_CLAIMS);}
    public static boolean isExPoration(String token){
        return getTokenBoby(token).getExpiration().before(new Date());
    }public static Claims getTokenBoby(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
    public static String getToken (HttpServletRequest request){
        String tokenHeader =request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if(null!=tokenHeader){
            String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX,"");
            return token;
        }String cookieTokenHeader =CookieUtils.getCookie(request,"tokenHeader");
        if(null!=cookieTokenHeader){
            String token = cookieTokenHeader.replace(JwtTokenUtils.TOKEN_COOKIE_PREFIX,"");
            return token;
        }
        else {
            return null;
        }
    }

}
