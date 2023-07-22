package com.example.springeurekabook.dtos;

import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
  
  public Integer id;
  @NonNull
  public String name; 
  
  @NonNull
  public Integer pageCount; 
  public Boolean status;
  
  @NonNull
  public Integer authorId; 
  public String authorFirstName;
  public String authorLastName;
  
  @NonNull
  public Integer typeId; 
  public String typeTitle;
}