package com.alekseytyan.logiweb.exception;

import com.alekseytyan.logiweb.exception.httpcode.AccessDeniedException;
import com.alekseytyan.logiweb.exception.httpcode.BadRequestException;
import com.alekseytyan.logiweb.exception.httpcode.InternalException;
import com.alekseytyan.logiweb.exception.httpcode.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomControllerAdvice {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/403");
        return mav;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFound() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/404");
        return mav;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalException.class)
    public ModelAndView internalError() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/500");
        return mav;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ModelAndView badRequest() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/400");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView anyException() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/defaultError");
        return mav;
    }
}
