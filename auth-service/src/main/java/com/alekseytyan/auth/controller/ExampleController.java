package com.alekseytyan.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Value("${example.property}")
    private String exampleProperty;

    @GetMapping(value = "/example")
    public String example() {
        return String.format(exampleProperty);
    }

}
