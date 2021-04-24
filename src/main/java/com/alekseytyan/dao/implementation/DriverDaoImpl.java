package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.DriverDao;
import com.alekseytyan.entity.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DriverDaoImpl extends AbstractDaoImpl<Driver, Long> implements DriverDao {

    public DriverDaoImpl() {
        super(Driver.class);
    }

    @Override
    public Driver findDriverByUser(String email) {
        return (Driver) entityManager
                .createNamedQuery("Driver.findByUser")
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Driver> findCoDrivers(Long orderId) {
        return entityManager
                .createNamedQuery("Driver.findCoDrivers")
                .setParameter("id", orderId)
                .getResultList();
    }
}
