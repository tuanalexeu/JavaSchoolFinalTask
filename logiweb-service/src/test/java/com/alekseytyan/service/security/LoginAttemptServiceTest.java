package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.service.implementation.security.LoginAttemptServiceImpl;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LoginAttemptServiceTest {

    LoginAttemptServiceImpl service;

    @Before
    public void init() {
        service = new LoginAttemptServiceImpl();
    }

    @Test
    @SneakyThrows
    public void loginSucceededTest() {

        service.loginSucceeded("key");

        assertFalse(service.isBlocked("key"));
    }


    @Test
    @SneakyThrows
    public void loginFailedTest() {

        for (int i = 0; i < 11; i++) {
            service.loginFailed("key");
        }

        assertTrue(service.isBlocked("key"));
    }
}
