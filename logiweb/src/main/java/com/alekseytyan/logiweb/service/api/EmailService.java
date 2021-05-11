package com.alekseytyan.logiweb.service.api;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
    void sendSimpleMessage(SimpleMailMessage email);
}
