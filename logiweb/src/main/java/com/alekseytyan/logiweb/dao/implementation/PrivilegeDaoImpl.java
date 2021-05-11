package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.PrivilegeDao;
import com.alekseytyan.logiweb.entity.security.Privilege;
import org.springframework.stereotype.Repository;

@Repository
public class PrivilegeDaoImpl extends AbstractDaoImpl<Privilege, Long> implements PrivilegeDao {
    public PrivilegeDaoImpl() {
        super(Privilege.class);
    }

    @Override
    public Privilege findByName(String name) {
        return entityManager.createNamedQuery("Privilege.findByName", Privilege.class)
                .setParameter("name", name)
                .getResultList().stream().findFirst().orElse(null);
    }
}
