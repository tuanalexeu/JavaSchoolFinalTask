package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dao.api.UserDao;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.User;
import com.alekseytyan.logiweb.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao, UserDTO, String> implements UserService {
    @Autowired
    public UserServiceImpl(UserDao dao, ModelMapper mapper) {
        super(dao, mapper, UserDTO.class, User.class);
    }
}
