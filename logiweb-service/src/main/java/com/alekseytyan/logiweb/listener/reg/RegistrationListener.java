package com.alekseytyan.logiweb.listener.reg;

import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.service.api.EmailService;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.api.VerificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    private final VerificationService verificationService;
    private final UserService userService;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {

        // Get new user
        User user = event.getUser();

        // Create new token using UUID
        String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(userService.convertToDTO(user), token);

        // Get user email and send confirmation message
        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/register-confirm?token=" + token;

        String message = "Registration went well";

        String text = message + "\r\n" + "http://localhost:8080" + confirmationUrl;

        emailService.sendSimpleMessage(recipientAddress, subject, text);

        logger.info("Email [" + subject + "] to " + recipientAddress + " was just sent");
    }
}