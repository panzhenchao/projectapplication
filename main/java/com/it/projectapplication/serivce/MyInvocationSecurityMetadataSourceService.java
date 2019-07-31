package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.PermissionDao;
import com.it.projectapplication.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private PermissionDao permissionDao;
    private HashMap<String, Collection<ConfigAttribute>> map=null;
    public void loadResourceDefine(){
        map =new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<Permission>permissions=permissionDao.findAll();
        for(Permission permission:permissions){
            array=new ArrayList<>();
            cfg=new SecurityConfig(permission.getName());
            array.add(cfg);
            map.put(permission.getUrl(),array);
        }
    }
   @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException{
        if (map == null) loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext();) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        } return null;

    }
    public Collection<ConfigAttribute>getAllConfigAttributes() {
        return null;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
