package com.it.projectapplication.dao;

import com.it.projectapplication.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User,Long> {
    public List<User>findAll();
    public User findAllByUsername(String username);

}
