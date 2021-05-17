package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Order;

import java.util.List;

public interface OrderDao extends AbstractDao<Order, Long> {
    List<Order> findVerified(Integer size, Integer page);
    List<Order> findVerified();
}
