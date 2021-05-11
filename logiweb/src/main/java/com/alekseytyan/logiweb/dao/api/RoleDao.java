package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.Role;

public interface RoleDao extends AbstractDao<Role, Long> {
    Role findByName(String role);
}
