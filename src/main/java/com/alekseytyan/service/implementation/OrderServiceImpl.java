package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.OrderDao;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Order;
import com.alekseytyan.service.api.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDao, OrderDTO, Long> implements OrderService {
    @Autowired
    public OrderServiceImpl(OrderDao dao, ModelMapper mapper) {
        super(dao, mapper, OrderDTO.class);
    }
}
