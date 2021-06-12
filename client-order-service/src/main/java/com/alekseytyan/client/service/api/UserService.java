package com.alekseytyan.client.service.api;

import com.alekseytyan.client.entity.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;


public interface UserService {
    List<User> findAll();
    User findById(String id);
    void registerNewUser(OAuth2User principal);
    void delete(User userDTO);
    void deleteById(String id);
}
