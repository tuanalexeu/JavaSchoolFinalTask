package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.City;

import java.util.List;

/**
 * Data access object to work with City table
 */
public interface CityDao extends AbstractDao<City, String> {
    /**
     * Finds all names
     * @return - list of names
     */
    List<String> findAllNames();
}
