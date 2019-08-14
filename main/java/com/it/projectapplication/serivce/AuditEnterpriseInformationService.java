package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.AuditEnterpriseInformationDao;
import com.it.projectapplication.domain.AuditEnterpriseInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditEnterpriseInformationService {
    @Autowired
    AuditEnterpriseInformationDao auditEnterpriseInformationDao;
    public AuditEnterpriseInformation findAuitEnterpriseInformationByUsername(String username){
        AuditEnterpriseInformation auditEnterpriseInformation=auditEnterpriseInformationDao.findAuditEnterpriseInformationByUsername(username);
        return auditEnterpriseInformation;
    }
}
