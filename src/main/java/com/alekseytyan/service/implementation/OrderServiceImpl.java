package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.OrderDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.City;
import com.alekseytyan.entity.Order;
import com.alekseytyan.service.api.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDao, OrderDTO, Long> implements OrderService {

    @Autowired
    public OrderServiceImpl(OrderDao dao, ModelMapper mapper) {
        super(dao, mapper, OrderDTO.class, Order.class);
    }

    @Override
    public int calculateWeight(Long orderId) {

        Order order = getDao().findById(orderId);

        // TODO calculate final weight of the order

        return 0;
    }

    @Override
    public int calculateRouteTime(Long orderId) {

        Order order = getDao().findById(orderId);

        // TODO calculate final time

        return 0;
    }

    @Override
    public List<City> calculateRouteCities(Long orderId) {
        Order order = getDao().findById(orderId);

        // TODO calculate route

        return new ArrayList<>();
    }

    @Override
    @Transactional
    public OrderDTO delete(OrderDTO orderDTO) {

        // Set order as null in dependencies
        for (DriverDTO d: orderDTO.getDrivers()) {
            d.setOrder(null);
        }
        orderDTO.getLorry().setOrder(null);

        OrderDTO refreshedOrderDTO = update(orderDTO);

        return super.delete(refreshedOrderDTO);
    }

    @Override
    @Transactional
    public OrderDTO deleteById(Long entityId) {

        OrderDTO orderDTO = findById(entityId);

        return delete(orderDTO);
    }
}
