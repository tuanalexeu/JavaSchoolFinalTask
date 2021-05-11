package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.Privilege;

public interface PrivilegeDao extends AbstractDao<Privilege, Long> {
    Privilege findByName(String name);
}
