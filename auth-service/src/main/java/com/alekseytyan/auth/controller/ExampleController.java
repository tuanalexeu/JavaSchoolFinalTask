package com.alekseytyan.auth.controller;

import com.alekseytyan.auth.config.RemoteServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private RemoteServerConfig config;

    @GetMapping(value = "/example")
    public String exampleEndpoint() {
        return String.format(config.getExampleProperty());
    }

}
