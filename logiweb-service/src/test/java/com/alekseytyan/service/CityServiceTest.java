package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.CityDaoImpl;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.service.implementation.CityServiceImpl;
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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
    @InjectMocks
    CityServiceImpl service;

    @Mock
    CityDaoImpl dao;

    @Mock
    ModelMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        List<City> list = new ArrayList<>();

        list.add(new City());
        list.add(new City());
        list.add(new City());

        when(dao.findAll()).thenReturn(list);

        //test
        List<City> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById("Angarsk")).thenReturn(
                new City("Angarsk")
        );

        City city = dao.findById("Angarsk");

        assertEquals("Angarsk", city.getName());
    }
}
