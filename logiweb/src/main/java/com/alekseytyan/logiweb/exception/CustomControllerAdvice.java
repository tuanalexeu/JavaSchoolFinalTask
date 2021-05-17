package com.alekseytyan.logiweb.exception;

import com.alekseytyan.logiweb.aspect.LogAnnotation;
import com.alekseytyan.logiweb.exception.httpcode.AccessDeniedException;
import com.alekseytyan.logiweb.exception.httpcode.BadRequestException;
import com.alekseytyan.logiweb.exception.httpcode.InternalException;
import com.alekseytyan.logiweb.exception.httpcode.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    @LogAnnotation
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/403");
        return mav;
    }

    @ExceptionHandler(NotFoundException.class)
    @LogAnnotation
    public ModelAndView notFound() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/404");
        return mav;
    }

    @ExceptionHandler(InternalException.class)
    @LogAnnotation
    public ModelAndView internalError() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/500");
        return mav;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @LogAnnotation
    public ModelAndView handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/400");

        return mav;
    }


    @ExceptionHandler(UserBlockedException.class)
    @LogAnnotation
    public ModelAndView userBlocked() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/userBlocked");
        return mav;
    }

    @ExceptionHandler(NoSuchRoleException.class)
    @LogAnnotation
    public ModelAndView noSuchRole() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/noSuchRole");
        return mav;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @LogAnnotation
    public ModelAndView anyException() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/defaultError");
        return mav;
    }

}
