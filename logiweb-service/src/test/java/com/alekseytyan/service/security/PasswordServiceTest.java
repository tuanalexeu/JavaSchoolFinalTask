package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.dao.api.PasswordResetTokenDao;
import com.alekseytyan.logiweb.dto.PasswordResetTokenDTO;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.implementation.security.PasswordServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(MockitoJUnitRunner.class)
public class PasswordServiceTest {

    @InjectMocks
    PasswordServiceImpl service;

    @Mock
    PasswordResetTokenDao dao;

    @Mock
    UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createPasswordResetTokenForUserTest() {
        assertDoesNotThrow(() -> {
            service.createPasswordResetTokenForUser(null, null);
        });
    }

    @Test
    public void validatePasswordResetTokenTest() {
        assertDoesNotThrow(() -> {
            service.validatePasswordResetToken("token");
        });
    }
}
