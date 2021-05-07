package com.alekseytyan.dao.api;

import com.alekseytyan.entity.Order;

import java.util.List;

public interface OrderDao extends AbstractDao<Order, Long> {
    List<Order> findVerified();
}
