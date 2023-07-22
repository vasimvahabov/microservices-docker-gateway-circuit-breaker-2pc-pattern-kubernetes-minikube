package com.example.springeurekarental.error;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor; 

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeignException extends RuntimeException{
	
  private static final long serialVersionUID = 11234L;
  @Nonnull
  private String code;
  @Nonnull
  private String message;
}
