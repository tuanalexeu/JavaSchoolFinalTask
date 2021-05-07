package com.alekseytyan.logiweb.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataMappingConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
