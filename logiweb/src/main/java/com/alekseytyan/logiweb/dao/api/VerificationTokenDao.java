package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.entity.auth.VerificationToken;

public interface VerificationTokenDao extends AbstractDao<VerificationToken, Long> {
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);

    void deleteAll();
}
