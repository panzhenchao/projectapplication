package com.it.projectapplication.dao;

import com.it.projectapplication.domain.PersonalInformation;
import org.springframework.data.repository.CrudRepository;

public interface PersonalInformationDao extends CrudRepository<PersonalInformation,Long> {

}
