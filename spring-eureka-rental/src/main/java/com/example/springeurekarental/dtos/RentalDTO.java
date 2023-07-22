package com.example.springeurekarental.dtos;

import org.springframework.lang.NonNull;
import java.time.LocalDateTime; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalDTO {
  public Integer id;
  public LocalDateTime pickUpDate;  
  public LocalDateTime dropOffDate;
  @NonNull
  public Integer userId;
  @NonNull
  public Integer bookId;    
}