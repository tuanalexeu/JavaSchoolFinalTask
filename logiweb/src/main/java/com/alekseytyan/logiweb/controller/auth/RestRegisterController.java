package com.alekseytyan.logiweb.controller.auth;


import com.alekseytyan.logiweb.service.api.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RestRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RestRegisterController.class);

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;

//    @PostMapping("/user/registration")
//    public GenericResponse registerUserAccount(
//            @Valid UserDTO accountDto, HttpServletRequest request) throws UserAlreadyExistException {
//
//        logger.debug("Registering user account with information: {}", accountDto);
//
//        UserDTO registered = createUserAccount(accountDto);
//        if (registered == null) {
//            throw new UserAlreadyExistException("User with such email already exists!");
//        }
//        String appUrl = "http://" + request.getServerName() + ":" +
//                request.getServerPort() + request.getContextPath();
//
//        eventPublisher.publishEvent(
//                new OnRegistrationCompleteEvent(userService.convertToEntity(registered), request.getLocale(), appUrl));
//
//        return new GenericResponse("success");
//    }

}
