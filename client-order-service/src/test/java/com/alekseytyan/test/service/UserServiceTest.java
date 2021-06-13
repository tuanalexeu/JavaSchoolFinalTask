package com.alekseytyan.test.service;

import com.alekseytyan.client.entity.User;
import com.alekseytyan.client.repository.UserRepository;
import com.alekseytyan.client.service.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
        when(repository.findById("1")).thenReturn(Optional.of(new User("1", "Aleksey")));

        User user = service.findById("1");

        assertEquals("1", user.getId());
        assertEquals("Aleksey", user.getName());
    }

    @Test
    public void registerNewUserTest() {

        User user = new User("1","Aleksey");

        Optional<User> optionalUser = Optional.of(user);

        when(repository.findById("1")).thenReturn(optionalUser);


        service.registerNewUser(new OAuth2User() {
            @Override
            public Map<String, Object> getAttributes() {
                Map<String, String> result = new HashMap<>();
                result.put("name", "Aleks2");
                return new HashMap<>();
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getName() {
                return "Aleks2";
            }
        });

        User user1 = service.findById("1");

        assertNotEquals("Aleks2", user1.getName());
    }

    @Test
    public void deleteTest() {
        when(repository.findById("1")).thenReturn(Optional.empty());
        service.delete(new User("1","Aleksey"));

        User user = service.findById("1");

        assertNull(user);
    }

    @Test
    public void deleteByIdTest() {
        when(repository.findById("1")).thenReturn(Optional.empty());
        service.deleteById("1");

        User user = service.findById("1");

        assertNull(user);
    }
}