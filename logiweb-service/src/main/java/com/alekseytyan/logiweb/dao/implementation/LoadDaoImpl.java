package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.LoadDao;
import com.alekseytyan.logiweb.entity.Load;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoadDaoImpl extends AbstractDaoImpl<Load, Long> implements LoadDao {

    public LoadDaoImpl() {
        super(Load.class);
    }

    @Override
    public Load findByToken(String token) {
        return entityManager.createNamedQuery("Load.findByToken", Load.class)
                .setParameter("token", token)
                .getSingleResult();
    }

    @Override
    public List<Load> findAllByClientId(String clientId) {
        return entityManager.createNamedQuery("Load.findAllByClientId", Load.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }
}
