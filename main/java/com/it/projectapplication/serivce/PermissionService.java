package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.PermissionDao;
import com.it.projectapplication.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    PermissionDao permissionDao;

    public Permission findById(Long id){
        Optional<Permission> optional =permissionDao.findById(id);
        return optional.get();
    }

}
