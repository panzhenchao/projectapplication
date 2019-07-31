package com.it.projectapplication.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtUser implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser() {

    }

    public JwtUser(User user, Collection grantedAuthoritie) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        authorities = grantedAuthoritie;


    }

    public Long getId() {
        return id;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "JwtUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
