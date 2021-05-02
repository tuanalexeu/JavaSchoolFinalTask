package com.alekseytyan.config;

import com.alekseytyan.config.security.WebSecurityConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.alekseytyan")
@Import(value = {
        DataSourceConfig.class,
        DataMappingConfig.class,
        WebConfig.class,
        WebSecurityConfig.class
})
public class AppConfig {
}