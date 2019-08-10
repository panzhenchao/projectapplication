package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.EnterpriseInformationDao;
import com.it.projectapplication.domain.EnterpriseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseInformationService {
    @Autowired
    EnterpriseInformationDao enterpriseInformationDao;
    public EnterpriseInformation findInformationByUsername(String username){
        EnterpriseInformation enterpriseInformation=enterpriseInformationDao.findEnterpriseInformationByUsername(username);
        return enterpriseInformation;
    }
}
