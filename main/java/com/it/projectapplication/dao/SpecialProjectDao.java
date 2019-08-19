package com.it.projectapplication.dao;

import com.it.projectapplication.domain.SpecialProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SpecialProjectDao extends PagingAndSortingRepository<SpecialProject,Long> {
    @Query(value = "SELECT * FROM special_project WHERE special_project.declare_subject & ?1 and special_project.state=1",nativeQuery = true)
    public List<SpecialProject> findListByDeclare(Integer typeNumber);
    public SpecialProject findSpecialProjectById(Long id);

}
