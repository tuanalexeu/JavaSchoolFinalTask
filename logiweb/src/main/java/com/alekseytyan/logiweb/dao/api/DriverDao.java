package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Driver;

import java.util.List;

public interface DriverDao extends AbstractDao<Driver, Long> {

    Driver findDriverByUser(String email);

    List<Driver> findCoDrivers(Long orderId);

    List<Driver> findSuitableDrivers(String cityName, int hours);

    List<Driver> findWithoutUser();

    long countAvailable();
    long countUnavailable();
}
