package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.dao.implementation.UserDaoImpl;
import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.enums.UserRole;
import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.service.implementation.DriverServiceImpl;
import com.alekseytyan.logiweb.service.implementation.security.UserServiceImpl;
import lombok.SneakyThrows;
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
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().silent();

    @InjectMocks
    UserServiceImpl service;

    @Mock
    DriverServiceImpl driverService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserDaoImpl dao;

    @Mock
    ModelMapper mapper;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void updateTest() {

        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(1L);

        when(driverService.findDriverByUser("email")).thenReturn(driverDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");
        userDTO.setDriver(new DriverDTO());

        assertDoesNotThrow(() -> {
            service.update(userDTO);
        });
    }

    @Test
    public void updateTest2() {

        when(driverService.findDriverByUser("email")).thenReturn(null);

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");

        assertDoesNotThrow(() -> {
            service.update(userDTO);
        });
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

    @Test
    public void deleteByIdTest() {
        when(driverService.findDriverByUser("email")).thenReturn(new DriverDTO());
        assertDoesNotThrow(() -> {
            service.deleteById("email");
        });
    }

    @Test
    public void deleteByIdTest2() {
        when(driverService.findDriverByUser("email")).thenReturn(null);
        assertDoesNotThrow(() -> {
            service.deleteById("email");
        });
    }

    @Test
    public void findDisabledTest() {
        assertDoesNotThrow(() -> {
            service.findDisabled(1,1);
        });
    }

    @Test
    public void findDisabledAndVerifiedTest() {
        assertDoesNotThrow(() -> {
            service.findDisabledAndVerified(1,1);
        });
    }

    @Test
    @SneakyThrows
    public void registerNewUserAccountTest() {
        when(dao.findById("email")).thenReturn(null);

        assertDoesNotThrow(() -> {
            service.registerNewUserAccount(new UserDTO());
        });
    }

    @Test
    @SneakyThrows
    public void registerNewUserAccountTest2() {
        when(dao.findById("email")).thenReturn(new User());

        assertThrows(UserAlreadyExistException.class, () -> {
            UserDTO user1 = new UserDTO();
            user1.setEmail("email");
            service.registerNewUserAccount(user1);
        });
    }

    @Test
    public void findWithoutDriverTest() {
        assertDoesNotThrow(() -> {
            service.findWithoutDriver(1,1);
        });
    }

    @Test
    public void deleteIfUnconfirmedTest() {
        assertDoesNotThrow(() -> service.deleteIfUnconfirmed("email"));
    }

}