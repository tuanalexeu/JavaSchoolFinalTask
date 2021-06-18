package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.dao.implementation.VerificationTokenDaoImpl;
import com.alekseytyan.logiweb.dto.VerificationTokenDTO;
import com.alekseytyan.logiweb.entity.auth.VerificationToken;
import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.implementation.security.VerificationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VerificationServiceTest {

    @InjectMocks
    VerificationServiceImpl service;

    @Mock
    UserService userService;

    @Mock
    VerificationTokenDaoImpl dao;

    @Mock
    ModelMapper mapper;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createVerificationTokenTest() {
        assertDoesNotThrow(() -> {
            service.createVerificationToken(null, null);
        });
    }

    @Test
    public void getVerificationTokenTest() {

        VerificationToken token = new VerificationToken(null, null);

        when(dao.getVerificationToken("token")).thenReturn(token);

        assertEquals(service.convertToDTO(new VerificationToken(null, null)), service.getVerificationToken("token"));
    }

    @Test
    public void deleteAllTest() {
        assertDoesNotThrow(() -> {
            service.deleteAll();
        });
    }
}
