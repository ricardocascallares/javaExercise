package com.example.BlogExercise.errorHandler;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {

    Logger logger = Logger.getLogger(ErrorHandler.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse runtimeExceptionResponse(RuntimeException ex){
        ErrorResponse errorResponse= new ErrorResponse(ex.getMessage());
        logger.error(errorResponse.toString());
        return errorResponse;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse badCredentialsExceptionResponse(BadCredentialsException ex){
        ErrorResponse errorResponse= new ErrorResponse(ex.getMessage());
        logger.error(errorResponse.toString());
        return errorResponse;
    }




}
