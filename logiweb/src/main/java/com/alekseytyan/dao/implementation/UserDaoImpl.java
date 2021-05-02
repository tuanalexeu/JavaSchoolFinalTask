package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.UserDao;
import com.alekseytyan.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }
}
