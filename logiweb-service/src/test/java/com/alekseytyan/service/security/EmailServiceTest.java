package com.alekseytyan.service.security;

import com.alekseytyan.logiweb.service.implementation.security.EmailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    EmailServiceImpl service;

    @Mock
    JavaMailSender emailSender;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sendSimpleEmail() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("logiweb-no-reply@inbox.ru");
        message.setTo("to");
        message.setSubject("subject");
        message.setText("text");

        assertDoesNotThrow(() -> {
            service.sendSimpleMessage("to", "subject", "text");
        });
    }

    @Test
    public void sendSimplePreparedEmail() {
        assertDoesNotThrow(() -> {
            service.sendSimpleMessage(new SimpleMailMessage());
        });
    }
}
