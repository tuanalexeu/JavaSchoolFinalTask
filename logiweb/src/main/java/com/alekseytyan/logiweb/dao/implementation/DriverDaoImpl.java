package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.DriverDao;
import com.alekseytyan.logiweb.entity.*;
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
    public List<Driver> findSuitableDrivers(String cityName, int hours) {
        return entityManager.
                createNamedQuery("Driver.findSuitableDrivers", Driver.class)
                .setParameter("cityName", cityName)
                .setParameter("hours", hours)
                .getResultList();
    }
}
