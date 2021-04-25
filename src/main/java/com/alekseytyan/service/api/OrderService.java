package com.alekseytyan.service.api;

import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Order;
import com.alekseytyan.util.LoadChecker;

public interface OrderService extends AbstractService<Order, OrderDTO, Long> {

    /**
     * Method checks if each load has both LOADING and UNLOADING points on the route
     * @param orderId - order, which contains route points list
     * @return - true, if each load has both points
     */
    LoadChecker checkRoutePoints(Long orderId);
}
