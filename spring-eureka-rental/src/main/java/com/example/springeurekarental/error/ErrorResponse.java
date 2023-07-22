package com.example.springeurekarental.error;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat; 
import jakarta.annotation.Nonnull;
import lombok.Data;
 
@Data
public class ErrorResponse{
	
  @Nonnull	
  private int statusCode_;  
	  
  @Nonnull	
  private String msg_;
	  
  @Nonnull	
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime throwedDate_;
	
  public ErrorResponse(){ 
    this.throwedDate_=LocalDateTime.now();
  }
	
  public ErrorResponse(int statusCode,String msg) {
    this.statusCode_=statusCode;
    this.msg_=msg;
    this.throwedDate_=LocalDateTime.now();
  }
}
