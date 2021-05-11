package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.PasswordResetTokenDao;
import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetTokenDaoImpl extends AbstractDaoImpl<PasswordResetToken, Long> implements PasswordResetTokenDao {

    public PasswordResetTokenDaoImpl() {
        super(PasswordResetToken.class);
    }
}
