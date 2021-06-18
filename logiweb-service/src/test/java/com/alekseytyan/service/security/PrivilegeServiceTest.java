package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.dao.implementation.PrivilegeDaoImpl;
import com.alekseytyan.logiweb.entity.security.Privilege;
import com.alekseytyan.logiweb.service.implementation.security.PrivilegeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrivilegeServiceTest {

    @InjectMocks
    PrivilegeServiceImpl service;

    @Mock
    PrivilegeDaoImpl dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findByNameTest() {
        when(dao.findByName("Alex")).thenReturn(new Privilege("Alex"));

        assertEquals("Alex", service.findByName("Alex").getName());
    }
}
