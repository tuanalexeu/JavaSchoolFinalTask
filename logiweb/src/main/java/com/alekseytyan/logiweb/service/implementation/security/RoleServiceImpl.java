package com.alekseytyan.logiweb.service.implementation.security;

import com.alekseytyan.logiweb.dao.api.RoleDao;
import com.alekseytyan.logiweb.dto.RoleDTO;
import com.alekseytyan.logiweb.entity.security.Role;
import com.alekseytyan.logiweb.service.api.RoleService;
import com.alekseytyan.logiweb.service.implementation.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, RoleDao, RoleDTO, Long> implements RoleService {
    public RoleServiceImpl(RoleDao dao, ModelMapper mapper) {
        super(dao, mapper, RoleDTO.class, Role.class);
    }

    @Override
    public Role findByName(String role) {
        return getDao().findByName(role);
    }
}
