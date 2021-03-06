package com.it.projectapplication.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it.projectapplication.domain.JwtUser;
import com.it.projectapplication.domain.User;
import com.it.projectapplication.model.LoginUser;
import com.it.projectapplication.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager =authenticationManager;
        super.setFilterProcessesUrl("/login");
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            LoginUser loginUser=new ObjectMapper().readValue(request.getInputStream(),LoginUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getUsername() ,loginUser.getPassword(), new ArrayList<>())
            );

        }catch (IOException e){
            e.printStackTrace();
            return null;

        }

    }
    public    Authentication attemptAuthentication(User user){
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername() ,user.getPassword(), new ArrayList<>())
        );
    }
    @Override
    protected  void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException{
        JwtUser jwtUser=(JwtUser) authResult.getPrincipal();
        System.out.println(jwtUser.toString());
        Collection<? extends GrantedAuthority> authorities=jwtUser.getAuthorities();
        List<String> permissionList=new ArrayList<String>();
        for (GrantedAuthority authority:authorities){
            permissionList.add(authority.getAuthority());
        }
        String token = JwtTokenUtils.createrToken(jwtUser.getUsername(),permissionList,false);
        response.setHeader("token",JwtTokenUtils.TOKEN_PREFIX + token);
        response.getWriter().write(token);

    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
