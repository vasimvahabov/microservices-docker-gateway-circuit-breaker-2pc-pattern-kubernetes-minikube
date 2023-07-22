package com.example.springeurekauser.models;

import org.springframework.lang.NonNull; 
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
  public Integer id; 
  public String firstName;
  public String lastName;   
  @NonNull
  public String email;
  public String password;
}
