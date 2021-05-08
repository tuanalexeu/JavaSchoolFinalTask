package com.alekseytyan.logiweb.controller.role.admin;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.service.api.DriverService;
import com.alekseytyan.logiweb.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final DriverService driverService;

    @Autowired
    public AdminController(UserService userService,
                           DriverService driverService) {
        this.userService = userService;
        this.driverService = driverService;
    }

    @GetMapping(value = "/users")
    public String viewUsers(Model model) {

        model.addAttribute("newUser", new UserDTO());
        model.addAttribute("users", userService.findAll());

        return "role/admin/users";
    }

    @GetMapping(value = "/approve-users")
    public String approveUsers(Model model) {

        model.addAttribute("newUsers", userService.findDisabled());

        return "role/admin/approve-users";
    }

    @PostMapping(value = "/approve-user")
    public RedirectView approveUser(@RequestParam String email) {

        UserDTO userDTO = userService.findById(email);
        userDTO.setEnabled(true);
        userService.update(userDTO);

        return new RedirectView("/admin/approve-users");
    }

    @GetMapping(value = "/approve-drivers")
    public String approveDrivers(Model model) {

        model.addAttribute("newDrivers", driverService.findWithoutUser());
        model.addAttribute("newUsers", userService.findWithoutDriver());

        return "role/admin/approve-drivers";
    }

    @PostMapping("/approve-driver")
    public RedirectView approveDriver(@RequestParam Long driverId, @RequestParam String email) {

        DriverDTO driverDTO = driverService.findById(driverId);
        driverDTO.setUser(userService.findById(email));
        driverService.update(driverDTO);

        return new RedirectView("/admin/approve-drivers");
    }



}
