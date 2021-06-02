package com.alekseytyan.dashboard.controller;

import com.alekseytyan.dashboard.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        userService.registerNewUser(principal);
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
}