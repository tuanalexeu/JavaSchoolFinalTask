package com.alekseytyan.dao.api;

import com.alekseytyan.entity.Driver;

import java.util.List;

public interface DriverDao extends AbstractDao<Driver, Long> {
    Driver findDriverByUser(String email);
    List<Driver> findCoDrivers(Long orderId);
}
