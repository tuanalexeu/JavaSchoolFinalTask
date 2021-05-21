package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.entity.Order;

import java.util.List;

/**
 * Service is used to calculate routes for orders
 */
public interface OrderService extends AbstractService<Order, OrderDTO, Long> {

    /**
     * Finds verified orders.
     * Version for pagination
     */
    List<OrderDTO> findVerified(Integer size, Integer page);

    /**
     * Finds verified orders
     */
    List<OrderDTO> findVerified();

    /**
     * Calculates route for given order
     */
    Route calculateRoute(OrderDTO orderDTO);

    /**
     * Calculates list of routes for given list of orders
     */
    List<Route> calculateRoute(List<OrderDTO> orderDTOList);

    /**
     * Calculate needed truck capacity
     */
    int calculateWeight(Order order);

}
