package com.it.projectapplication.dao;

import com.it.projectapplication.domain.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PermissionDao extends CrudRepository<Permission,Long> {
    public List<Permission> findAllById(String id);
    @Query("SELECT 0 FROM Permission WHERE ID=1")
    public List<Permission> findAll();
    @Query("SELECT 0 FROM Permission WHERE ID=1")
    public List<Permission>findByUserName(String username);
    public List<Permission>findAllByName(String name);
}
