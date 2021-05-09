package com.alekseytyan.logiweb.listener.reg;

import com.alekseytyan.logiweb.entity.User;
import com.alekseytyan.logiweb.service.api.EmailService;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.api.VerificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
 
    private final VerificationService verificationService;
    private final UserService userService;
    private final MessageSource messages;
    private final EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        verificationService.createVerificationToken(userService.convertToDTO(user), token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
          = event.getAppUrl() + "/register-confirm?token=" + token;
        String message = messages.getMessage("message.regSucc", null, event.getLocale());

        String text = message + "\r\n" + "http://localhost:8080" + confirmationUrl;

        emailService.sendSimpleMessage(recipientAddress, subject, text);
    }
}