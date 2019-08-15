package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.PersonalInformationDao;
import com.it.projectapplication.domain.PersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonalInformationService {
    @Autowired
    PersonalInformationDao personalInformationDao;
    public PersonalInformation findPersonalInformationByUsername(String username){
        PersonalInformation personalInformation=personalInformationDao.findPersonalInformationByUsername(username);
        return personalInformation;
    }
    public void savePersonalInformation(PersonalInformation personalInformation){
        personalInformationDao.save(personalInformation);
    }
    public Page<PersonalInformation> findPersonalInformationByaddressAndPageble(String address, Pageable pageable){

        Page<PersonalInformation> page=personalInformationDao.findPersonalInformationsByAddressIsLike(address,pageable);
        return page;
    }

}
