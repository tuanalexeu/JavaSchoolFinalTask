package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.LorryDao;
import com.alekseytyan.logiweb.entity.Lorry;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LorryDaoImpl extends AbstractDaoImpl<Lorry, String> implements LorryDao {
    public LorryDaoImpl() {
        super(Lorry.class);
    }

    @Override
    public List<Lorry> findSuitableLorries(int weight) {
        return entityManager
                .createNamedQuery("Lorry.findSuitableLorries", Lorry.class)
                .setParameter("weight", weight)
                .getResultList();
    }

    @Override
    public long countAvailable() {
        return entityManager.createNamedQuery("Lorry.countAvailable", Long.class).getSingleResult();
    }

    @Override
    public long countUnavailable() {
        return entityManager.createNamedQuery("Lorry.countUnavailable", Long.class).getSingleResult();
    }

    @Override
    public long countBroken() {
        return entityManager.createNamedQuery("Lorry.countBroken", Long.class).getSingleResult();
    }
}
