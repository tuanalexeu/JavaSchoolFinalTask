package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.Role;

/**
 * Data access object to work with Role table
 */
public interface RoleDao extends AbstractDao<Role, Long> {
    Role findByName(String role);
}
