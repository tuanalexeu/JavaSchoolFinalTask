package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Lorry;

import java.util.List;

/**
 * Data access object to work with Lorry table
 */
public interface LorryDao extends AbstractDao<Lorry, String> {
    /**
     * Finds suitable lorries that have no order assigned
     * @param weight - needed capacity to complete the order
     * @param cityName - order city
     * @return - list of needed entities
     */
    List<Lorry> findSuitableLorries(int weight, String cityName);

    /**
     * Counts available lorries
     */
    long countAvailable();

    /**
     * Counts unavailable lorries
     */
    long countUnavailable();

    /**
     * Counts broken lorries
     */
    long countBroken();
}
