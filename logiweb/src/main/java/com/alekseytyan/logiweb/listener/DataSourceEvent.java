package com.alekseytyan.logiweb.listener;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter @Setter
public class DataSourceEvent extends ApplicationEvent {

    private String message;

    public DataSourceEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}