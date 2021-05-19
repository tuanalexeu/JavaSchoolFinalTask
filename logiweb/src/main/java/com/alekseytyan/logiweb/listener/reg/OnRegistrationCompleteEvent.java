package com.alekseytyan.logiweb.listener.reg;

import com.alekseytyan.logiweb.entity.security.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Registration event is used to keep metadata of registered user
 */
@Getter @Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private String appUrl;
    private Locale locale;
    private User user;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);
        
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}