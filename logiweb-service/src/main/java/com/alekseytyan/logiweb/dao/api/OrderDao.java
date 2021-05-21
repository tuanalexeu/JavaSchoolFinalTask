package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Order;

import java.util.List;

/**
 * Data access object to work with Order table
 */
public interface OrderDao extends AbstractDao<Order, Long> {

    /**
     * Finds orders which are configured and the route is possible.
     * Version for pagination.
     */
    List<Order> findVerified(Integer size, Integer page);

    /**
     * Finds orders which are configured and the route is possible
     */
    List<Order> findVerified();
}
