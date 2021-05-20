package com.alekseytyan.logiweb.config.schedule.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        logger.debug("Exception message - " + throwable.getMessage());
        logger.debug("Method name - " + method.getName());
        for (Object param : obj) {
            logger.debug("Parameter value - " + param);
        }
    }
    
}