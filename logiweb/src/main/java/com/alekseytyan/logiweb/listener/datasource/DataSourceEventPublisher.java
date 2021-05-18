package com.alekseytyan.logiweb.listener.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DataSourceEventPublisher {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DataSourceEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(final String message) {
        DataSourceEvent event = new DataSourceEvent(this, message);
        applicationEventPublisher.publishEvent(event);

        logger.info("Data source with message [" + message + "] event was published");
    }
}