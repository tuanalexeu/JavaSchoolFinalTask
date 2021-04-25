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
    public List<Driver> findCoDrivers(Long orderId) {
        return entityManager
                .createNamedQuery("Driver.findCoDrivers", Driver.class)
                .setParameter("id", orderId)
                .getResultList();
    }

    @Override
    public List<Driver> findSuitableDrivers() {
        return entityManager.
                createQuery("Driver.findSuitableDrivers", Driver.class)
                .getResultList();
    }
}
