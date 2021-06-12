package com.alekseytyan.test.controller;

import com.alekseytyan.client.ClientOrderService;
import com.alekseytyan.client.controller.AuthController;
import com.alekseytyan.client.controller.WelcomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ClientOrderService.class)
public class ControllerTest {

    @Autowired
    private WelcomeController welcomeController;

    @Autowired
    private AuthController authController;

    @Autowired
    private ClientOrderService clientOrderService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(authController).isNotNull();
        assertThat(welcomeController).isNotNull();
        assertThat(clientOrderService).isNotNull();
    }

}