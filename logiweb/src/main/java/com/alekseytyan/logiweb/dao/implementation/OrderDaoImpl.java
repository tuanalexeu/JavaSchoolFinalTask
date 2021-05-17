package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.OrderDao;
import com.alekseytyan.logiweb.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order, Long> implements OrderDao {
    public OrderDaoImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> findVerified(int size, int page) {
        Query query = entityManager.createNamedQuery("Order.findVerified", Order.class);
        return queryPage(query, size, page);
    }
}
