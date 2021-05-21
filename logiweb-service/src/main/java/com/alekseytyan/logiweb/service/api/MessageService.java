package com.alekseytyan.logiweb.service.api;

/**
 * Service is used to send messages over RabbitMQ
 */
public interface MessageService {
    /**
     * Sends simple text message
     */
    void send(String message);
}
