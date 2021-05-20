package com.alekseytyan.logiweb.service.api;

import org.springframework.mail.SimpleMailMessage;

/**
 * Service is used to support email sending
 */
public interface EmailService {

    /**
     * Sent new email with given configuration
     */
    void sendSimpleMessage(String to, String subject, String text);

    /**
     * Sent prepared email object
     */
    void sendSimpleMessage(SimpleMailMessage email);
}
