package com.it.projectapplication.dao;

import com.it.projectapplication.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao  extends CrudRepository<Role,Long> {
}
