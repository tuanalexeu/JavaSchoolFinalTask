package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.UserDao;
import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.entity.User;
import com.alekseytyan.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao, UserDTO, String> implements UserService {
    @Autowired
    public UserServiceImpl(UserDao dao, ModelMapper mapper) {
        super(dao, mapper, UserDTO.class, User.class);
    }

    @Override
    public List<UserDTO> findDisabled() {
        return convertToDTO(getDao().findDisabled());
    }
}
