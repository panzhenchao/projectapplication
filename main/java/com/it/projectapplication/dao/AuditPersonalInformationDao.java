package com.it.projectapplication.dao;

import com.it.projectapplication.domain.AuditPersonalInformation;
import org.springframework.data.repository.CrudRepository;

public interface AuditPersonalInformationDao extends CrudRepository<AuditPersonalInformation,Long> {
        public AuditPersonalInformation findAuditPersonalInformationByUsername(String username);

}
