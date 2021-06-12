package com.alekseytyan.client.service.implementation;

import com.alekseytyan.client.entity.User;
import com.alekseytyan.client.repository.UserRepository;
import com.alekseytyan.client.service.api.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void registerNewUser(OAuth2User principal) {
        if(!userRepository.findById(principal.getName()).isPresent()) {
            userRepository.save(
                    new User(principal.getName(), principal.getAttribute("name"))
            );

            logger.info("User [" + principal.getAttribute("name") + "] was successfully registered");

        }
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

}
