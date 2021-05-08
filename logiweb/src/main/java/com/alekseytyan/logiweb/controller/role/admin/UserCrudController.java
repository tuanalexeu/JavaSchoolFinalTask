package com.alekseytyan.logiweb.controller.role.admin;

import com.alekseytyan.logiweb.controller.auth.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/admin")
public class UserCrudController {

    private final UserService userService;

    @Autowired
    public UserCrudController(UserService userService) {

        this.userService = userService;

    }

    @PostMapping(value = "/add-user")
    public RedirectView addUser(@ModelAttribute UserDTO newUser) throws UserAlreadyExistException {

        userService.registerNewUserAccount(newUser);

        return new RedirectView("/admin/users");
    }

    @PostMapping(value = "/edit-user")
    public String editUser(Model model, @RequestParam String email) {

        model.addAttribute("editUser", userService.findById(email));

        return "role/admin/edit-user";
    }

    @PostMapping(value = "/save-user")
    public RedirectView saveUser(@ModelAttribute UserDTO userDTO) {

        userService.update(userDTO);

        return new RedirectView("/admin/users");
    }

    @PostMapping(value = "/delete-user")
    public RedirectView deleteUser(@RequestParam String email) {

        userService.deleteById(email);

        return new RedirectView("/admin/users");
    }


}
