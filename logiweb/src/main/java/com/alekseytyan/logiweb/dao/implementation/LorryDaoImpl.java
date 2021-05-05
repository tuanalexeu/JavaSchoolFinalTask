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
}
