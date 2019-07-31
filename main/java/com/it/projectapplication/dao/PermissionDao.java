package com.it.projectapplication.dao;

import com.it.projectapplication.domain.Permission;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionDao extends CrudRepository<Permission,Long> {
    public List<Permission> findAllById();
    public List<Permission> findAll();
    public List<Permission>findByUserName(String username);
}
