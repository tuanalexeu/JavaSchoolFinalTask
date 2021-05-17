package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.UserDao;
import com.alekseytyan.logiweb.entity.security.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> findDisabled(int size, int page) {
        Query query = entityManager.createNamedQuery("User.findDisabled", User.class);
        return queryPage(query, size, page);
    }

    @Override
    public List<User> findWithoutDriver(int size, int page) {
        Query query =  entityManager.createNamedQuery("User.findWithoutDriver", User.class);
        return queryPage(query, size, page);
    }

    @Override
    public List<User> findDisabledAndVerified(int size, int page) {
        Query query = entityManager.createNamedQuery("User.findVerifiedAndDisabled", User.class);
        return queryPage(query, size, page);
    }

    @Override
    public void deleteIfUnconfirmed(String email) {
        entityManager.createNamedQuery("User.deleteIfUnconfirmed").setParameter("email", email);
    }
}
