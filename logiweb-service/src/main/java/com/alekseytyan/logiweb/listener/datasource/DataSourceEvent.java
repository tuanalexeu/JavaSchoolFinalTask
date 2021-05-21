package com.alekseytyan.logiweb.listener.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * Event that is handled when a CRUD operation with database is committed
 */
@Getter @Setter
public class DataSourceEvent extends ApplicationEvent {

    /**
     * Message to send to the 2nd application
     */
    private String message;

    public DataSourceEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}