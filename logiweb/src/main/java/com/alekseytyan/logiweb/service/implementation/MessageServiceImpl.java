package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public MessageServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String message) {
        jmsTemplate.send(s -> s.createObjectMessage(message));
    }
}
