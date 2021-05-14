package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.service.api.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Topic;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

     private final JmsTemplate jmsTemplate;

     private final Topic topic;

    @Autowired
    public MessageServiceImpl(JmsTemplate jmsTemplate, Topic topic) {
        this.jmsTemplate = jmsTemplate;
        this.topic = topic;
    }

    @Override
    public void send(String message) {

         jmsTemplate.send(topic, s -> s.createObjectMessage(message));

        logger.info("Message's been sent");
    }
}
