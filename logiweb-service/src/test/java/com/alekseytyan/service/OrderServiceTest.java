package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.OrderDaoImpl;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.entity.DistanceMap;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.entity.Order;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.implementation.MapServiceImpl;
import com.alekseytyan.logiweb.service.implementation.OrderServiceImpl;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.util.pathfinding.RouteChecker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.*;

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
    MapServiceImpl mapService;

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
    public void deleteByIdTest() {
        assertThrows(NullPointerException.class, () -> service.deleteById(-1L));
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
        List<DistanceMap> distanceMaps = new ArrayList<>(Collections.singletonList(
                new DistanceMap(1L, new City("Angarsk"), new City("Irkutsk"), 100)
        ));

        Route route = RouteChecker.calculateRoute(distanceMaps, new ArrayList<>(Collections.singletonList(
                new Load(1L, new City("Angarsk"), new City("Irkutsk"),
                        null, "exampleName", 0, LoadStatus.PREPARED, null, null)
        )));

        assertTrue(route.isPossible());
        assertEquals(new ArrayList<>(Arrays.asList(new City("Angarsk"), new City("Irkutsk"))), route.getCityList());
        assertEquals(100, route.getDistance());
        assertEquals(0, route.getMaxWeight());
        assertEquals(1, route.getTime());


    }

    @Test
    public void calculateRouteTest2() {
        assertDoesNotThrow(() -> {
            service.calculateRoute(new ArrayList<>());
        });
    }

    @Test
    public void calculateWeightTest() {
        assertEquals(0, RouteChecker.calculateMaxWeight(new ArrayList<>()));
    }

}