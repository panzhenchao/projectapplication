package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.ManagerInformationDao;
import com.it.projectapplication.domain.ManagerInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerInformationService {
    @Autowired
    ManagerInformationDao managerInformationDao;
    public ManagerInformation findManagerInformationByUserName(String username){

        ManagerInformation managerInformation=managerInformationDao.findManagerInformationByUsername(username);
        return managerInformation;
    }

}
