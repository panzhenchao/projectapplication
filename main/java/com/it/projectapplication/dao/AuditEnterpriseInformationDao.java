package com.it.projectapplication.dao;

import com.it.projectapplication.domain.AuditEnterpriseInformation;
import org.springframework.data.repository.CrudRepository;

public interface AuditEnterpriseInformationDao extends CrudRepository<AuditEnterpriseInformation,Long> {
    public AuditEnterpriseInformation findAuditEnterpriseInformationByUsername(String username);
}
