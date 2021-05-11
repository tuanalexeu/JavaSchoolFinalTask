package com.alekseytyan.logiweb.service.implementation.security;

import com.alekseytyan.logiweb.dao.api.PrivilegeDao;
import com.alekseytyan.logiweb.dto.PrivilegeDTO;
import com.alekseytyan.logiweb.entity.security.Privilege;
import com.alekseytyan.logiweb.service.api.PrivilegeService;
import com.alekseytyan.logiweb.service.implementation.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl extends AbstractServiceImpl<Privilege, PrivilegeDao, PrivilegeDTO, Long> implements PrivilegeService {
    public PrivilegeServiceImpl(PrivilegeDao dao, ModelMapper mapper) {
        super(dao, mapper, PrivilegeDTO.class, Privilege.class);
    }

    @Override
    public Privilege findByName(String name) {
        return getDao().findByName(name);
    }
}
