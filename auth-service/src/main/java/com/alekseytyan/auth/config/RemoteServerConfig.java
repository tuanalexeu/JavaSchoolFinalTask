package com.alekseytyan.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
public class RemoteServerConfig {

    @Value("${example.property}")
    private String exampleProperty;

}
