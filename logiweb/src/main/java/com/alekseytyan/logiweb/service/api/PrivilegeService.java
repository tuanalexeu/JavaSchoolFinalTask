package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.PrivilegeDTO;
import com.alekseytyan.logiweb.entity.security.Privilege;

public interface PrivilegeService extends AbstractService<Privilege, PrivilegeDTO, Long> {
    Privilege findByName(String name);
}
