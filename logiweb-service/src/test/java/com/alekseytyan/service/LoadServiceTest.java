package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.api.LoadDao;
import com.alekseytyan.logiweb.dao.implementation.LoadDaoImpl;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.implementation.LoadServiceImpl;
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
public class LoadServiceTest {

    @InjectMocks
    LoadServiceImpl service;

    @Mock
    LoadDaoImpl dao;

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
}