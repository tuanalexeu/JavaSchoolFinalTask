package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.entity.Order;
import com.alekseytyan.logiweb.util.pathfinding.Route;

import java.util.List;
import java.util.Map;

public interface OrderService extends AbstractService<Order, OrderDTO, Long> {

    List<OrderDTO> findVerified();

    Route calculateRoute(OrderDTO orderDTO);

    List<Route> calculateRoute(List<OrderDTO> orderDTOList);

    Map<Long, String> convertLoadsToMap(OrderDTO orderDTO);

    int calculateWeight(Order order);

    List<LoadDTO> convertLoadsToList(Map<Long, String> loads, DriverDTO driverDTO);
}
