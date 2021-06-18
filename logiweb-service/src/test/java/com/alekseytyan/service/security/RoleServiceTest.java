package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.dao.implementation.RoleDaoImpl;
import com.alekseytyan.logiweb.entity.security.Role;
import com.alekseytyan.logiweb.service.implementation.security.RoleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @InjectMocks
    RoleServiceImpl service;

    @Mock
    RoleDaoImpl dao;

    @Mock
    ModelMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findByNameTest() {
        when(dao.findByName("ROLE_EXAMPLE")).thenReturn(new Role("ROLE_EXAMPLE"));
        assertEquals(new Role("ROLE_EXAMPLE").getName(), service.findByName("ROLE_EXAMPLE").getName());
    }
}
