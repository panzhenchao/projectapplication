package com.it.projectapplication.dao;

import com.it.projectapplication.domain.Project;
import com.it.projectapplication.domain.SpecialProject;
import com.it.projectapplication.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProjectDao extends PagingAndSortingRepository<Project,Long> {
    @Query(value = "select max(number) from Project ")
    public Long findMaxNumber();

    public Project findProjectByid(Long id);

    public List<Project> findProjectsByUser(User user);

    public List<Project> findProjectsByAddressIsLikeAndSpecialProjectAndState(String address, SpecialProject specialProject,Integer state);

    public  List<Project> findProjectsByVillageDepartmentStateAndSpecialProject(Integer VillageDepartmentState,SpecialProject specialProject);


}
