package com.alekseytyan.dashboard.dao.api;

import java.util.List;

/**
 * Data access object is used to interact with database
 * @param <E> - Entity type
 * @param <ID> - Entity primary key type
 */
public interface AbstractDao<E, ID> {

    /**
     * Finds entity with given ID
     * @param id - given ID
     * @return - found entity
     */
    E findById(ID id);

    /**
     * Select all rows from database
     * @return - list of entities
     */
    List<E> findAll();

    /**
     * Select all rows with given size and page
     * @param size - size of page
     * @param page - number of page
     * @return - paginated list of entities
     */
    List<E> findPage(Integer size, Integer page);

    /**
     * Saves given entity
     * @param entity - given entity
     * @return - managed entity
     */
    E save(E entity);

    /**
     * Updates given entity
     * @param entity - given entity
     * @return - updated managed entity
     */
    E update(E entity);

    /**
     * Delete given entity
     * @param entity - given entity
     * @return - deleted entity
     */
    E delete(E entity);

    /**
     * Delete entity with given iD
     * @param entityId - given ID
     * @return - deleted entity
     */
    E deleteById(ID entityId);

}
