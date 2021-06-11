package com.alekseytyan.service;


import com.alekseytyan.logiweb.dao.implementation.DriverDaoImpl;
import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.entity.enums.DriverState;
import com.alekseytyan.logiweb.service.implementation.DriverServiceImpl;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {

    @InjectMocks
    DriverServiceImpl service;

    @Mock
    DriverDaoImpl dao;

    @Mock
    ModelMapper mapper;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        List<Driver> list = new ArrayList<>();

        list.add(new Driver());
        list.add(new Driver());
        list.add(new Driver());

        when(dao.findAll()).thenReturn(list);

        //test
        List<Driver> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById(1L)).thenReturn(
                new Driver(1L, "A","T",1, DriverState.DRIVING,
                        null,null,null,null)
        );

        Driver driver = dao.findById(1L);

        assertEquals("A", driver.getFirstName());
        assertEquals("T", driver.getLastName());
        assertEquals(1, driver.getHoursWorked());
        assertEquals(DriverState.DRIVING, driver.getState());
    }
}