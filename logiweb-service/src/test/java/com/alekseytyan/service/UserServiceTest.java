package com.alekseytyan.service;

import com.alekseytyan.logiweb.dao.implementation.UserDaoImpl;
import com.alekseytyan.logiweb.entity.enums.UserRole;
import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.service.implementation.security.UserServiceImpl;
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
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl service;

    @Mock
    UserDaoImpl dao;

    @Mock
    ModelMapper mapper;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        List<User> list = new ArrayList<>();

        list.add(new User());
        list.add(new User());
        list.add(new User());

        when(dao.findAll()).thenReturn(list);

        //test
        List<User> empList = service.convertToEntity(service.findAll());

        assertEquals(3, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(dao.findById("example@gmail.com")).thenReturn(
                new User("example@gmail.com", "password", "A", "T",
                        UserRole.ROLE_ADMIN, null, true, false)
        );

        User user = dao.findById("example@gmail.com");

        assertEquals("password", user.getPassword());
        assertEquals("A", user.getFirstName());
        assertEquals("T", user.getLastName());
        assertEquals(UserRole.ROLE_ADMIN, user.getRole());
        assertTrue(user.isEnabled());
        assertFalse(user.isEmailConfirmed());
    }

}