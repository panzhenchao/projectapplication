package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.EnterpriseInformationDao;
import com.it.projectapplication.domain.EnterpriseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseInformationService {
    @Autowired
    EnterpriseInformationDao enterpriseInformationDao;
    public EnterpriseInformation findEnterpriseInformationByUsername(String username){
        EnterpriseInformation enterpriseInformation=enterpriseInformationDao.findEnterpriseInformationByUsername(username);
        return enterpriseInformation;
    }
    public void saveEnterpriseInformation(EnterpriseInformation enterpriseInformation){
        enterpriseInformationDao.save(enterpriseInformation);
    }
}
