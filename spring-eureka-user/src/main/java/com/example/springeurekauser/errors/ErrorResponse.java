package com.example.springeurekauser.errors;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape; 
 
public class ErrorResponse {
  public final int _statusCode;
  public final String _message;
  @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",shape = Shape.STRING)
  public final Date _throwedDate;
  
  public ErrorResponse(int statusCode,String message) {
    this._statusCode=statusCode;
    this._message=message;
    this._throwedDate=new Date();
  }
}
