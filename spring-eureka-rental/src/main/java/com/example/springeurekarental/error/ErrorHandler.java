package com.example.springeurekarental.error;

import java.lang.reflect.UndeclaredThrowableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

//  @ExceptionHandler(Feign.class)
//  public ResponseEntity<ErrorResponse> handleException(Exception exception){
//	String msg=exception.getMessage();
//    log.error(msg+" in @ExceptionHandler(Exception.class)");
//    
//    ErrorResponse errorResponse=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
//    return ResponseEntity.ok(errorResponse);
//  }  
  
  
  @ExceptionHandler(UndeclaredThrowableException.class)
  public ResponseEntity<ErrorResponse> handleUndeclaredThrowableException(
		  							         UndeclaredThrowableException exception){
	String msg=exception.getMessage();
    log.error(msg+" in @ExceptionHandler(UndeclaredThrowableException.class)"); 
    
    ErrorResponse errorResponse=new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),msg);
    return ResponseEntity.ok(errorResponse);
  } 
  
  
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
		  									 IllegalArgumentException exception){
	String msg=exception.getMessage();
    log.error(msg+" in @ExceptionHandler(IllegalArgumentException.class)");
    
    ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),msg);
    return ResponseEntity.ok(errorResponse);
  }
  
  @ExceptionHandler(FeignException.class)
  public ResponseEntity<ErrorResponse> handleFeignException(RuntimeException exception){
	String msg=exception.getMessage();
    log.error(msg+" in @ExceptionHandler(FeignException.class)");
    
    ErrorResponse errorResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(),msg);
    return ResponseEntity.ok(errorResponse);
  } 
}
