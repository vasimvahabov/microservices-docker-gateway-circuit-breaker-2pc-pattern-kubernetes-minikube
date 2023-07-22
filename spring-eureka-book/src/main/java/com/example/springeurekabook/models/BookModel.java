package com.example.springeurekabook.models;

import org.springframework.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookModel {
	
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