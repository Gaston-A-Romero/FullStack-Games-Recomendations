package com.Reviews.Controller;

import com.Reviews.Exceptions.ContentNotFoundException;
import com.Reviews.Exceptions.ControlException;
import com.Reviews.Exceptions.NotYourPropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProfileControllerExceptions {
    @ExceptionHandler(ContentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleProfileNotFoundException(ContentNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NotYourPropertyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleNotYourPropertyException(NotYourPropertyException ex){return ex.getMessage();}

    @ExceptionHandler(ControlException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleNotYourPropertyException(ControlException ex){return ex.getMessage();}


}
