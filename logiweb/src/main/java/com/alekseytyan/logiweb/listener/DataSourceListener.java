package com.alekseytyan.logiweb.listener;

import com.alekseytyan.logiweb.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DataSourceListener implements ApplicationListener<DataSourceEvent> {

    private final MessageService messageService;

    @Autowired
    public DataSourceListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onApplicationEvent(DataSourceEvent event) {
        messageService.send(event.getMessage());
    }
    
}