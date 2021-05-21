//package com.alekseytyan.logiweb.controller.authRest;
//
//
//import com.alekseytyan.logiweb.dto.UserDTO;
//import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
//import com.alekseytyan.logiweb.listener.reg.OnRegistrationCompleteEvent;
//import com.alekseytyan.logiweb.service.api.UserService;
//import com.alekseytyan.logiweb.util.response.GenericResponse;
//import lombok.AllArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping(value = "/rest")
//@AllArgsConstructor
//public class RestRegisterController {
//
//    private static final Logger logger = LoggerFactory.getLogger(RestRegisterController.class);
//
//    private final UserService userService;
//    private final ApplicationEventPublisher eventPublisher;
//
//    @PostMapping("/register")
//    public GenericResponse registerUserAccount(@Valid UserDTO accountDto, HttpServletRequest request) throws UserAlreadyExistException {
//
//        logger.debug("Registering user account with information: {}", accountDto);
//
//        UserDTO registered = userService.registerNewUserAccount(accountDto);
//
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
//
//}
