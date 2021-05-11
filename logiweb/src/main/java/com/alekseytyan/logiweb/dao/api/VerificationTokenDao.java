package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.auth.User;
import com.alekseytyan.logiweb.entity.auth.VerificationToken;

public interface VerificationTokenDao extends AbstractDao<VerificationToken, Long> {
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);
}
