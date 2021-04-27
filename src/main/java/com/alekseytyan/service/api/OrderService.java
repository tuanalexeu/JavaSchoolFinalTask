package com.alekseytyan.service.api;

import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Order;

public interface OrderService extends AbstractService<Order, OrderDTO, Long> {
    /**
     * Method calculates final weight of order regarding LOADING & UNLOADING points all the way through
     * @param orderId - order id we need to calculate the weight of
     * @return - number in kilograms
     */
    int calculateWeight(Long orderId);

    /**
     * Method calculates final time that whole route takes
     * @param orderId - order id time of which we need to calculate
     * @return - time as hours
     */
    int calculateRouteTime(Long orderId);
}
