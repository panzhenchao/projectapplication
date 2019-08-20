package com.it.projectapplication.dao;

import com.it.projectapplication.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDao extends CrudRepository<User,Long> {
    public List<User>findAll();

    public User findUserByUsername(String username);
    public User findUserById(Long id);
    public User findUserByUsernameAndPassword(String username,String password);

    @Query(value = "select max(id) from User ")
    public Long findMaxId();


}
