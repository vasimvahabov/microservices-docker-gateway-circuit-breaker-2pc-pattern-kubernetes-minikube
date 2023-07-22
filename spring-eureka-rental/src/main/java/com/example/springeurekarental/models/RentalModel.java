package com.example.springeurekarental.models;

import java.time.LocalDateTime;
import org.springframework.lang.NonNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalModel {
  public Integer id;
  
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",shape =Shape.STRING)
  public LocalDateTime pickUpDate; 
  
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",shape =Shape.STRING)
  public LocalDateTime dropOffDate;
  
  @NonNull
  public Integer userId;
  
  @NonNull
  public Integer bookId;  
}