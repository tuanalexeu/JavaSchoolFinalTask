package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.api.LorryDao;
import com.alekseytyan.logiweb.dao.implementation.LorryDaoImpl;
import com.alekseytyan.logiweb.entity.Lorry;
import com.alekseytyan.logiweb.service.implementation.LorryServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LorryServiceTest {

    @InjectMocks
    LorryServiceImpl service;

    @Mock
    LorryDaoImpl dao;

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
        List<Lorry> list = new ArrayList<>();

        list.add(new Lorry());
        list.add(new Lorry());
        list.add(new Lorry());

        when(dao.findAll()).thenReturn(list);

        //test
        List<Lorry> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById("AB12345")).thenReturn(
                new Lorry("AB12345", 1, 1, true, null, null)
        );

        Lorry lorry = dao.findById("AB12345");

        assertEquals("AB12345", lorry.getRegNum());
        assertEquals(1, lorry.getShiftTime());
        assertEquals(1, lorry.getCapacity());
        assertTrue(lorry.isBroken());
    }
}