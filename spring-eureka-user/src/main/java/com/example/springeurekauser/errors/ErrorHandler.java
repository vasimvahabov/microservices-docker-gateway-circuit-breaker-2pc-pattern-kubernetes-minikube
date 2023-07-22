package com.example.springeurekauser.errors;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandler extends ExceptionHandlerExceptionResolver{

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException exception){
    String msg=exception.getMessage();
    int statusCode=HttpStatus.UNAUTHORIZED.value();
    
    log.warn(msg+" in @ExceptionHandler(EntityNotFoundException.class)");
    ErrorResponse response=new ErrorResponse(statusCode, msg);
    return ResponseEntity.ok(response);
  }
  
  @ExceptionHandler(JWTVerificationException.class)
  public ResponseEntity<Void> handleJWTDecodeException(JWTVerificationException exception){
    String msg=exception.getMessage();
//    int statusCode=HttpStatus.UNAUTHORIZED.value();
    
    log.warn(msg+" in @ExceptionHandler(JWTVerificationException.class)");
//    ErrorResponse response=new ErrorResponse(statusCode, msg);
    return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
  }
}
