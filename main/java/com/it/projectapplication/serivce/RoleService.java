package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.RoleDao;
import com.it.projectapplication.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;
    public List<Role> findAll(){
        List<Role> list=(List<Role>) roleDao.findAll();
        return list;

    }
    public Role findRole(Integer roleId){
        Long id=(long)roleId;
        Optional<Role> role=roleDao.findById(id);
        return role.get();

    }
    public void saveRole(Role role){

        roleDao.save(role);
    }
}
