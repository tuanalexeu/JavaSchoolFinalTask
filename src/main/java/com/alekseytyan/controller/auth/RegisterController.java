package com.alekseytyan.controller.auth;

import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.service.api.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/register")
public class RegisterController {

    private final EmailService emailService;

    @Autowired
    public RegisterController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(value = "/confirm-email")
    public RedirectView confirmEmail(@ModelAttribute UserDTO userDTO) {
        return new RedirectView();
    }


    public RedirectView registerUser() {
        return new RedirectView();
    }
}
