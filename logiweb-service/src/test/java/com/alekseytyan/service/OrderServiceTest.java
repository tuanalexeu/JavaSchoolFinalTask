package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.MapDaoImpl;
import com.alekseytyan.logiweb.dao.implementation.OrderDaoImpl;
import com.alekseytyan.logiweb.dto.*;
import com.alekseytyan.logiweb.entity.*;
import com.alekseytyan.logiweb.service.implementation.MapServiceImpl;
import com.alekseytyan.logiweb.service.implementation.OrderServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class OrderServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();

    @InjectMocks
    OrderServiceImpl service;

    @Mock
    OrderDaoImpl dao;

    @Mock
    MapServiceImpl mapService;

    @Mock
    MapDaoImpl mapDao;

    @Mock
    ModelMapper mapper;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void saveTest() {
        service.save(null);

        verify(dao, times(1)).save(null);
    }

    @Test
    public void updateTest() {
        service.update(null);

        verify(dao, times(1)).update(null);
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

    @Test
    public void deleteTest() {
        assertDoesNotThrow(() -> {
            service.delete(new OrderDTO());
        });
    }

    @Test
    public void deleteTest2() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setDrivers(new ArrayList<>());
        orderDTO.setLorry(new LorryDTO());

        assertDoesNotThrow(() -> {
            service.delete(orderDTO);
        });
    }

    @Test
    public void deleteByIdTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);
        orderDTO.setDrivers(new ArrayList<>());
        orderDTO.setLorry(new LorryDTO());

        Order order = new Order();
        order.setId(1L);
        order.setDrivers(new ArrayList<>());
        order.setLorry(new Lorry());

        when(mapper.map(order, OrderDTO.class)).thenReturn(orderDTO);
        when(mapper.map(orderDTO, Order.class)).thenReturn(order);

        when(dao.findById(1L)).thenReturn(order);

        assertDoesNotThrow(() -> {
            service.deleteById(1L);
        });
    }

    @Test
    public void findVerifiedTest() {
        when(dao.findVerified(1,1)).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), service.findVerified(1,1));
        verify(dao, times(1)).findVerified(1,1);
    }

    @Test
    public void findVerifiedTest2() {
        when(dao.findVerified()).thenReturn(new ArrayList<>());

        assertEquals(new ArrayList<>(), service.findVerified());
        verify(dao, times(1)).findVerified();
    }

    @Test
    public void calculateRouteTest() {

        DistanceMap distanceMap = new DistanceMap();
        List<DistanceMap> distanceMaps = new ArrayList<>(Collections.singletonList(distanceMap));

        MapDTO mapDTO = new MapDTO();
        List<MapDTO> dtoList = new ArrayList<>(Collections.singletonList(mapDTO));

        when(mapper.map(mapDTO, DistanceMap.class)).thenReturn(distanceMap);
        when(mapper.map(distanceMap, MapDTO.class)).thenReturn(mapDTO);

        Order order = new Order();
        Load load = new Load();
        load.setCityLoad(new City("Angarsk"));
        load.setCityUnload(new City("Irkutsk"));
        order.setLoads(new ArrayList<>(Collections.singletonList(load)));

        OrderDTO orderDTO = new OrderDTO();
        LoadDTO loadDTO = new LoadDTO();
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("Angarsk");
        loadDTO.setCityLoad(cityDTO);
        orderDTO.setLoads(new ArrayList<>(Collections.singletonList(loadDTO)));

        when(mapper.map(order, OrderDTO.class)).thenReturn(orderDTO);
        when(mapper.map(orderDTO, Order.class)).thenReturn(order);

        when(mapDao.findAll()).thenReturn(distanceMaps);

        assertDoesNotThrow(() -> {
            service.calculateRoute(orderDTO);
        });

    }

    @Test
    public void calculateRouteTest2() {
        assertDoesNotThrow(() -> {
            service.calculateRoute(new ArrayList<>());
        });
    }

    @Test
    public void calculateWeightTest() {
        Order order = new Order();
        order.setLoads(new ArrayList<>());

        assertEquals(0, service.calculateWeight(order));
    }

}