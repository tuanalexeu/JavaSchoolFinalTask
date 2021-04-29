package com.alekseytyan.service.api;

import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.City;
import com.alekseytyan.entity.Order;
import com.alekseytyan.util.Route;

import java.util.List;

public interface OrderService extends AbstractService<Order, OrderDTO, Long> {
    Route calculateRoute(OrderDTO order);
}
