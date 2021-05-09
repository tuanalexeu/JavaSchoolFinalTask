package com.alekseytyan.logiweb.service.api;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
