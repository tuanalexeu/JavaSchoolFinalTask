package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.LorryDao;
import com.alekseytyan.entity.Lorry;
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
