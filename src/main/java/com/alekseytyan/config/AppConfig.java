package com.alekseytyan.config;

import com.alekseytyan.config.security.WebSecurityConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.alekseytyan")
@Import(value = {
        DataSourceConfig.class,
        DataMappingConfig.class,
        WebConfig.class,
        WebSecurityConfig.class
})
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(DataSource.class);
    }
}