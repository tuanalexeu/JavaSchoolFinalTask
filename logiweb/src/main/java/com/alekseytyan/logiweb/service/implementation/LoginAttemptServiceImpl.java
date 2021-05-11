package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.service.api.LoginAttemptService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

//@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptServiceImpl() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        try {

            int count = attemptsCache.get(key);

            return count >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
