package com.alekseytyan.logiweb.service.api;

public interface LoginAttemptService {

    boolean isBlocked(String key);

    void loginFailed(String remoteAddr);

    void loginSucceeded(String remoteAddr);
}
