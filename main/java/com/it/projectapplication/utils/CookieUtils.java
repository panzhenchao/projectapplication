package com.it.projectapplication.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

public class CookieUtils {
    public static String getCookie(HttpServletRequest request,String cookieName){
        Cookie []cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    public static void setCookie(HttpServletResponse response,String cookieName,String cookieValue) throws Exception{
        Cookie cookie=new Cookie(cookieName, URLEncoder.encode(cookieValue,"UTF-8"));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/projectapplication");
        response.addCookie(cookie);
    }
    public static void clearCookie(HttpServletRequest request,HttpServletResponse response,String cookieName){
        Cookie []cookies=request.getCookies();
        try {

           if(null!=cookies){
                for(Cookie cookie:cookies){
                    if(cookie.getName().equals(cookieName)) {
                        cookie.setMaxAge(0);
                        cookie.setPath("/projectapplication");
                        response.addCookie(cookie);
                    }
                }
            }

        }catch (Exception e){
            System.out.println("清除cookie"+cookieName+"异常");
        }
    }
}
