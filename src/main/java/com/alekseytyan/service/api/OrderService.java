package com.alekseytyan.service.api;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Order;
import com.alekseytyan.util.pathfinding.Route;

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
