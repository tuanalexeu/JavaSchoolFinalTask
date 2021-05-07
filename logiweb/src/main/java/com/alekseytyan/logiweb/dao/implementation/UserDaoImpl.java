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
}
