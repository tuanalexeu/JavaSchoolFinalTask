package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.service.api.MessageService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Service
@PropertySource("classpath:messaging.properties")
public class MessageServiceImpl implements MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private Environment environment;

    Connection connection;
    Channel channel;

    @SneakyThrows
    @PostConstruct
    public void init() throws IOException, TimeoutException, NoSuchAlgorithmException, KeyManagementException {

        // Establish connection with host localhost
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(environment.getProperty("rabbitmq.host"));
        connectionFactory.setUsername(environment.getProperty("rabbitmq.user"));
        connectionFactory.setPassword(environment.getProperty("rabbitmq.password"));
        connectionFactory.useSslProtocol();

        connection = connectionFactory.newConnection();
        channel = connection.createChannel();

        // Declare new queue named Logiweb
        channel.queueDeclare("Logiweb", false, false, false, null);
    }


    @Override
    public void send(String message) {

        try {
            // Publish basic text message
            channel.basicPublish("", "Logiweb", null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Message [" + message + "] has been sent");
    }
}
