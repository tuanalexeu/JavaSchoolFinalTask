package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.UserDao;
import com.alekseytyan.logiweb.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> findDisabled() {
        return entityManager.createNamedQuery("User.findDisabled", User.class).getResultList();
    }

    @Override
    public List<User> findWithoutDriver() {
        return entityManager.createNamedQuery("User.findWithoutDriver", User.class).getResultList();
    }

    @Override
    public List<User> findDisabledAndVerified() {
        return entityManager.createNamedQuery("User.findVerifiedAndDisabled", User.class).getResultList();
    }
}
