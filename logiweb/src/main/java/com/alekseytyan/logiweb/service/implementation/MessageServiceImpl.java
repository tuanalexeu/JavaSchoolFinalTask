package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.service.api.MessageService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    Connection connection;
    Channel channel;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        connection = connectionFactory.newConnection();
        channel = connection.createChannel();

        channel.queueDeclare("Logiweb", false, false, false, null);
    }


    @Override
    public void send(String message) {

        try {
            channel.basicPublish("", "Logiweb", null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Message [" + message + "] has been sent");
    }
}
