package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.UserDao;
import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.entity.User;
import com.alekseytyan.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao, UserDTO> implements UserService {

    @Autowired
    public UserServiceImpl(UserDao dao, ModelMapper mapper) {
        super(dao, mapper);
    }

    @Override
    public UserDTO convertToDTO(User entity) {
        return getMapper().map(entity, UserDTO.class);
    }
}
