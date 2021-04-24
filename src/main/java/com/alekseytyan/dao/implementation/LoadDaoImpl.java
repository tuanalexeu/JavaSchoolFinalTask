package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.LoadDao;
import com.alekseytyan.entity.Load;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoadDaoImpl extends AbstractDaoImpl<Load, Long> implements LoadDao {
    public LoadDaoImpl() {
        super(Load.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Load> findByOrderId(Long orderId) {
        return entityManager
                .createNamedQuery("Load.findByOrderId")
                .setParameter("id", orderId)
                .getResultList();
    }
}
