package com.it.projectapplication.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public static void setCookie(HttpServletResponse response,String cookienName,String cookieValue){
        Cookie myCookie=new Cookie(cookienName,cookieValue);
        myCookie.setMaxAge(60*60*24);
        response.addCookie(myCookie);
    }
}
