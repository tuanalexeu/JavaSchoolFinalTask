package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.OrderDao;
import com.alekseytyan.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }
}
