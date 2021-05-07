package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.OrderDao;
import com.alekseytyan.logiweb.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findVerified() {
        return entityManager
                .createNamedQuery("Order.findVerified", Order.class)
                .getResultList();
    }
}
