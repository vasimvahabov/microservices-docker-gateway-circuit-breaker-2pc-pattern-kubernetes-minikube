package com.example.springeurekauser.dtos;

import org.springframework.lang.NonNull; 
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  public Integer id;   
  public String firstName; 
  public String lastName;
  @NonNull
  public String email;  
  public String password;
}
