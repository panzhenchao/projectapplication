package com.it.projectapplication.config;

import com.it.projectapplication.utils.CookieUtils;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }
    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader =request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if(tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)){
             tokenHeader= CookieUtils.getCookie(request,"tokenHeader");
             if(tokenHeader!=null&&tokenHeader.startsWith(JwtTokenUtils.TOKEN_COOKIE_PREFIX)){
                 tokenHeader=tokenHeader.replace(JwtTokenUtils.TOKEN_COOKIE_PREFIX,JwtTokenUtils.TOKEN_PREFIX);
             }
             if(tokenHeader ==null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_COOKIE_PREFIX)){
                chain.doFilter(request,response);
                return;
            }
        }if(null!=tokenHeader) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
            super.doFilterInternal(request, response, chain);
        }
    }
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){

        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX,"");
        String username = JwtTokenUtils.getUsername(token);
        List<String> list=JwtTokenUtils.getUserPermission(token);
        if(username !=null){
            return new UsernamePasswordAuthenticationToken(username,null, list.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        }
        return null;
    }
}

