package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.LoadDaoImpl;
import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.entity.Order;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.implementation.LoadServiceImpl;
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
public class LoadServiceTest {

    @InjectMocks
    LoadServiceImpl service;

    @Mock
    LoadDaoImpl dao;

    @Mock
    OrderServiceImpl orderService;

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
    public void findAllTest() {
        List<Load> list = new ArrayList<>();

        list.add(new Load());
        list.add(new Load());
        list.add(new Load());

        when(dao.findAll()).thenReturn(list);

        //test
        List<Load> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById(1L)).thenReturn(
                new Load(1L, null,null,null, "example-load",
                        1, LoadStatus.SENT,null,null)
        );

        Load load = dao.findById(1L);

        assertEquals("example-load", load.getName());
        assertEquals(1, load.getWeight());
        assertEquals(LoadStatus.SENT, load.getStatus());
    }

    @Test
    public void findOrderIdTest() {

        Order order = new Order();
        order.setId(2L);

        when(dao.findById(1L)).thenReturn(new Load(1L, new City("From"), new City("To"), order,
                null, 0, null, null, null));

        assertEquals(2L, service.findOrderId(1L));
    }

    @Test
    public void findByTokenTest() {

        Load load = new Load();
        load.setId(1L);

        when(dao.findByToken("token")).thenReturn(load);

        assertNull(service.findByToken("token"));
        verify(dao, times(1)).findByToken("token");
    }

    @Test
    public void findAllByClientIdTest() {
        when(dao.findAllByClientId("clientId")).thenReturn(new ArrayList<>());
        service.findAllByClientId("clientId");
        verify(dao, times(1)).findAllByClientId("clientId");
    }

    @Test
    public void saveClientLoadTest() {
        assertDoesNotThrow(() -> {
            service.save(new LoadDTO());
        });
    }

    @Test
    public void updateTest() {
        assertThrows(NullPointerException.class, () -> {
            service.update(null);
        });
    }

    @Test
    public void deleteTest() {
        assertThrows(NullPointerException.class, () -> {
            service.delete(null);
        });
    }

    @Test
    public void deleteByIdTest() {
        assertThrows(NullPointerException.class, () -> {
            service.deleteById(-1L);
        });
    }

}