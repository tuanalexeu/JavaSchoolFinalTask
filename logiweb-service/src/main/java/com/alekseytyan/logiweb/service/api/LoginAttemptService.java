package com.alekseytyan.logiweb.service.api;

/**
 * Service is used to handle logic attempts
 */
public interface LoginAttemptService {

    /**
     * Check if a user is blocked
     * @param key - IP, which user connect from
     * @return - true, if user is blocked
     */
    boolean isBlocked(String key);

    /**
     * Increases number of wrong attempts
     * @param remoteAddr - IP
     */
    void loginFailed(String remoteAddr);

    /**
     * Nullifies number of wrong attempts and logs a user in
     * @param remoteAddr - IP
     */
    void loginSucceeded(String remoteAddr);
}
