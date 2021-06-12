package com.alekseytyan.client.exception;

import com.alekseytyan.client.aspect.LogAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);

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


    @ExceptionHandler(NoHandlerFoundException.class)
    @LogAnnotation
    public ModelAndView noHandlerFound(HttpServletResponse response) {

        logger.info("Error status: " + response.getStatus());

        ModelAndView mav = new ModelAndView();

        switch (response.getStatus()) {
            case 404: mav.setViewName("error/404"); break;
            case 400: mav.setViewName("error/400"); break;
            case 403: mav.setViewName("error/403"); break;
            case 500: mav.setViewName("error/500"); break;
            default: mav.setViewName("error/defaultError");
        }

        return mav;
    }
}

