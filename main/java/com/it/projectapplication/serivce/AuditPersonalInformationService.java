package com.it.projectapplication.serivce;

import com.it.projectapplication.dao.AuditPersonalInformationDao;
import com.it.projectapplication.domain.AuditPersonalInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditPersonalInformationService {
    @Autowired
    AuditPersonalInformationDao auditPersonalInformationDao;
    public void saveAuditPersonalInformation(AuditPersonalInformation auditPersonalInformation){
        auditPersonalInformationDao.save(auditPersonalInformation);
    }
    public AuditPersonalInformation findAuditPersonInformationByUsername(String username){
        AuditPersonalInformation auditPersonalInformation=auditPersonalInformationDao.findAuditPersonalInformationByUsername(username);
        return auditPersonalInformation;
    }
}
