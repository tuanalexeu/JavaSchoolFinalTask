package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.entity.Order;

import java.util.List;

public interface OrderService extends AbstractService<Order, OrderDTO, Long> {

    List<OrderDTO> findVerified(int size, int page);

    Route calculateRoute(OrderDTO orderDTO);

    List<Route> calculateRoute(List<OrderDTO> orderDTOList);

    int calculateWeight(Order order);

}
