package com.alekseytyan.logiweb.listener.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DataSourceEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DataSourceEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(final String message) {
        DataSourceEvent event = new DataSourceEvent(this, message);
        applicationEventPublisher.publishEvent(event);
    }
}