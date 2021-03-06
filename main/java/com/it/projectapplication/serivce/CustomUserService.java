package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.PermissionDao;
import com.it.projectapplication.dao.UserDao;
import com.it.projectapplication.domain.JwtUser;
import com.it.projectapplication.domain.Permission;
import com.it.projectapplication.domain.Role;
import com.it.projectapplication.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    public UserDetails loadUserByUsername(String username){
        User user=userDao.findUserByUsername(username);
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        if(user!=null){
            Set<Role>roleSet=user.getRoles();
            for(Role role :roleSet){
                Set<Permission> permissionSet=role.getPermissions();
                for(Permission permission:permissionSet){
                    if(permission!=null&&permission.getName()!=null){
                        GrantedAuthority grantedAuthority=new SimpleGrantedAuthority(permission.getName());
                        grantedAuthorities.add(grantedAuthority);
                    }
                }
            }


            return new JwtUser(user,grantedAuthorities);

        }else{
            throw  new UsernameNotFoundException("admin"+username+"do not exist");
        }
    }
}
