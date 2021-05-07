package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.UserDao;
import com.alekseytyan.entity.User;
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
