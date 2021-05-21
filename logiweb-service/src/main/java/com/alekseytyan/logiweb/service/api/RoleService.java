package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.RoleDTO;
import com.alekseytyan.logiweb.entity.security.Role;

public interface RoleService extends AbstractService<Role, RoleDTO, Long> {
    Role findByName(String role);
}
