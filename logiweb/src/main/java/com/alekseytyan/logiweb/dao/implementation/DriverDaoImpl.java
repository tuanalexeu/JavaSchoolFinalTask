package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.DriverDao;
import com.alekseytyan.logiweb.entity.Driver;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DriverDaoImpl extends AbstractDaoImpl<Driver, Long> implements DriverDao {

    public DriverDaoImpl() {
        super(Driver.class);
    }

    @Override
    public Driver findDriverByUser(String email) {
        return entityManager
                .createNamedQuery("Driver.findByUser", Driver.class)
                .setParameter("email", email)
                .getResultList()
                .stream().findFirst().orElse(null);
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

    @Override
    public List<Driver> findWithoutUser() {
        return entityManager.createNamedQuery("Driver.findWithoutUser", Driver.class).getResultList();
    }

    @Override
    public long countAvailable() {
        return entityManager.createNamedQuery("Driver.countAvailable", Long.class).getSingleResult();
    }

    @Override
    public long countUnavailable() {
        return entityManager.createNamedQuery("Driver.countUnavailable", Long.class).getSingleResult();
    }
}
