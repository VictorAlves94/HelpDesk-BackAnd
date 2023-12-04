package com.victor.HelpDesk.resources.exceptions;

import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandardError> objectnotFoundExcepition(ObjectnotFoundException ex, HttpServletRequest request){
     StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
             "Object Not FOund", ex.getMessage(),request.getRequestURI());
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
