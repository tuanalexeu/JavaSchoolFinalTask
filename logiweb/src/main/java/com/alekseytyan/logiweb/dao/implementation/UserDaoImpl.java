package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.UserDao;
import com.alekseytyan.logiweb.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {
    public UserDaoImpl() {
        super(User.class);
    }
}
