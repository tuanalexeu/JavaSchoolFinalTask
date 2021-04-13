package com.alekseytyan.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.alekseytyan")
@Import(value = {
        DataSourceConfig.class
})
public class AppConfig {
}