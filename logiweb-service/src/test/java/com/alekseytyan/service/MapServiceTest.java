package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.CityDaoImpl;
import com.alekseytyan.logiweb.dao.implementation.MapDaoImpl;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.entity.DistanceMap;
import com.alekseytyan.logiweb.service.implementation.CityServiceImpl;
import com.alekseytyan.logiweb.service.implementation.MapServiceImpl;
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
public class MapServiceTest {
    @InjectMocks
    MapServiceImpl service;

    @Mock
    MapDaoImpl dao;

    @Mock
    ModelMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        List<DistanceMap> list = new ArrayList<>();

        list.add(new DistanceMap());
        list.add(new DistanceMap());
        list.add(new DistanceMap());

        when(dao.findAll()).thenReturn(list);

        //test
        List<DistanceMap> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById(1L)).thenReturn(
                new DistanceMap(1L,new City("Angarsk"), new City("Irkutsk"), 100)
        );

        DistanceMap map = dao.findById(1L);

        assertEquals(100, map.getDistance());
    }
}
