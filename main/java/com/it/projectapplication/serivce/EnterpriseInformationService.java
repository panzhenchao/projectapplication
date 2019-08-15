package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.EnterpriseInformationDao;
import com.it.projectapplication.domain.EnterpriseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<EnterpriseInformation> findEnterpriseInformationByaddressAndPageble(String address, Pageable pageable){
        Page<EnterpriseInformation> page=enterpriseInformationDao.findEnterpriseInformationsByAddressIsLike(address,pageable);
        return page;
    }

}
