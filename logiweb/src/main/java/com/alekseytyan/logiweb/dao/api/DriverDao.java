package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Driver;

import java.util.List;

/**
 * Data access object to work with Driver table
 */
public interface DriverDao extends AbstractDao<Driver, Long> {

    /**
     * Finds driver which has user with given email
     * @param email - given email
     * @return - needed entity
     */
    Driver findDriverByUser(String email);

    /**
     * Finds co-drivers that have the same order
     * @param orderId - order
     * @return - list of co-drivers
     */
    List<Driver> findCoDrivers(Long orderId);

    /**
     * Finds drivers that have no order assigned
     * @param cityName - driver city
     * @param hours - needed hours to complete the order
     * @return - list of needed entities
     */
    List<Driver> findSuitableDrivers(String cityName, int hours);

    /**
     * Finds drivers that have no user assigned
     * @return - list of needed entites
     */
    List<Driver> findWithoutUser();

    /**
     * Counts available drivers
     */
    long countAvailable();

    /**
     * Counts unavailable drivers
     */
    long countUnavailable();
}
