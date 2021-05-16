package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.VerificationTokenDao;
import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.entity.auth.VerificationToken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class VerificationTokenDaoImpl extends AbstractDaoImpl<VerificationToken, Long> implements VerificationTokenDao {

    public VerificationTokenDaoImpl() {
        super(VerificationToken.class);
    }

    @Override
    @Transactional
    public void createVerificationToken(User user, String token) {
        entityManager.persist(new VerificationToken(user, token));
    }

    @Override
    @Transactional(readOnly = true)
    public VerificationToken getVerificationToken(String token) {
        return entityManager
                .createNamedQuery("VerificationToken.findByToken",VerificationToken.class)
                .setParameter("token", token)
                .getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM VerificationToken");
    }
}
