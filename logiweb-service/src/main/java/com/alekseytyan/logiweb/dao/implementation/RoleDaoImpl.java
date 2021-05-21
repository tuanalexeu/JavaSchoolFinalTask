package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.RoleDao;
import com.alekseytyan.logiweb.entity.security.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role, Long> implements RoleDao {
    public RoleDaoImpl() {
        super(Role.class);
    }

    @Override
    public Role findByName(String role) {
        return entityManager.createNamedQuery("Role.findByName", Role.class)
                .setParameter("name", role)
                .getResultList().stream().findFirst().orElse(null);
    }
}
