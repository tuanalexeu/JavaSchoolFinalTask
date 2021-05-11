package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.PasswordResetTokenDao;
import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import com.alekseytyan.logiweb.entity.auth.User;
import org.springframework.stereotype.Repository;

@Repository
public class PasswordResetTokenDaoImpl extends AbstractDaoImpl<PasswordResetToken, Long> implements PasswordResetTokenDao {

    public PasswordResetTokenDaoImpl() {
        super(PasswordResetToken.class);
    }

    @Override
    public void createPasswordResetToken(User user, String token) {
        PasswordResetToken tokenEntity = new PasswordResetToken();
        tokenEntity.setUser(user);
        tokenEntity.setToken(token);

        entityManager.persist(tokenEntity);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return entityManager
                .createNamedQuery("PasswordResetToken.findByToken", PasswordResetToken.class)
                .setParameter("token", token)
                .getResultList().stream().findFirst().orElse(null);
    }
}
