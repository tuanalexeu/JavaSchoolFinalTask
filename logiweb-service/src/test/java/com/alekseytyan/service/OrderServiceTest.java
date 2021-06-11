package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.OrderDaoImpl;
import com.alekseytyan.logiweb.entity.Order;
import com.alekseytyan.logiweb.service.implementation.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    OrderServiceImpl service;

    @Mock
    OrderDaoImpl dao;

    @Mock
    ModelMapper mapper;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        List<Order> list = new ArrayList<>();

        list.add(new Order());
        list.add(new Order());
        list.add(new Order());

        when(dao.findAll()).thenReturn(list);

        //test
        List<Order> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById(1L)).thenReturn(
                new Order(1L, false, null, null, null, true)
        );

        Order order = dao.findById(1L);

        assertFalse(order.isFinished());
        assertTrue(order.isVerified());
    }
}