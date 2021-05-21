package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.Privilege;

/**
 * Data access object to work with Privilege table
 */
public interface PrivilegeDao extends AbstractDao<Privilege, Long> {
    /**
     * Finds privilege by name
     * @param name - name of privilege
     * @return - needed entity
     */
    Privilege findByName(String name);
}
