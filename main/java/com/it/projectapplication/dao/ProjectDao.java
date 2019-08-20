package com.it.projectapplication.dao;

import com.it.projectapplication.domain.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProjectDao extends PagingAndSortingRepository<Project,Long> {
    @Query(value = "select max(number) from Project ")
    public Long findMaxNumber();
}
