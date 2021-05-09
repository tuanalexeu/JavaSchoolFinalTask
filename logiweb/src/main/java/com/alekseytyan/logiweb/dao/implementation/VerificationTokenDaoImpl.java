package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.VerificationTokenDao;
import com.alekseytyan.logiweb.entity.User;
import com.alekseytyan.logiweb.entity.VerificationToken;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenDaoImpl extends AbstractDaoImpl<VerificationToken, Long> implements VerificationTokenDao {

    public VerificationTokenDaoImpl() {
        super(VerificationToken.class);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        entityManager.persist(new VerificationToken(user, token));
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return entityManager
                .createNamedQuery("VerificationToken.findByToken",VerificationToken.class)
                .setParameter("token", token)
                .getSingleResult();
    }
}
