package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.OrderDao;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Order;
import com.alekseytyan.entity.RoutePoint;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.util.LoadChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDao, OrderDTO, Long> implements OrderService {

    @Autowired
    public OrderServiceImpl(OrderDao dao, ModelMapper mapper) {
        super(dao, mapper, OrderDTO.class, Order.class);
    }

    @Override
    @Transactional(readOnly = true)
    public LoadChecker checkRoutePoints(Long orderId) {

        List<RoutePoint> routePointList = getDao().findById(orderId).getRoutePoints();

        // TODO iterate collection and find LOADING & UNLOADING points

        return new LoadChecker("TODO", new ArrayList<>());
    }

    @Override
    public Long calculateWeight(Long orderId) {

        Order order = getDao().findById(orderId);

        // TODO calculate final weight of the order

        return 0L;
    }
}
