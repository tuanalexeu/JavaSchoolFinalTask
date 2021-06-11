package com.alekseytyan.controller;

import com.alekseytyan.logiweb.LogiwebService;
import com.alekseytyan.logiweb.controller.authRest.RestAuthController;
import com.alekseytyan.logiweb.controller.rest.CityRestController;
import com.alekseytyan.logiweb.controller.rest.InfoTableController;
import com.alekseytyan.logiweb.controller.rest.OrderRestController;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LogiwebService.class)
public class RestControllerTest {

    @Autowired
    private CityRestController cityRestController;

    @Autowired
    private InfoTableController infoTableController;

    @Autowired
    private OrderRestController orderRestController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(cityRestController).isNotNull();
        assertThat(infoTableController).isNotNull();
        assertThat(orderRestController).isNotNull();
    }



}
