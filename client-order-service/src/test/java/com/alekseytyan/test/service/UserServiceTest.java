package com.alekseytyan.test.service;

import com.alekseytyan.client.entity.User;
import com.alekseytyan.client.repository.UserRepository;
import com.alekseytyan.client.service.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl service;

    @Spy
    UserRepository repository;

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

        when(repository.findAll()).thenReturn(list);

        //test
        List<User> resultList = service.findAll();


        assertEquals(3, resultList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void findByIdTest() {
        when(repository.findById("1")).thenReturn(java.util.Optional.of(new User("1", "Aleksey")));

        Optional<User> optionalUser = repository.findById("1");

        User user = optionalUser.get();

        assertEquals("1", user.getId());
        assertEquals("Aleksey", user.getName());
    }
}