package com.alekseytyan.dashboard.service.implementation;

import com.alekseytyan.dashboard.entity.User;
import com.alekseytyan.dashboard.repository.UserRepository;
import com.alekseytyan.dashboard.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

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
        if(userRepository.findById(principal.getName()).isPresent()) {
            userRepository.save(
                    new User(principal.getName(), principal.getAttribute("name"))
            );
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
