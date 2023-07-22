package com.example.springeurekagateway.config;
 
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class Beans{
  @Bean
  HttpStatus defaultStatus() {
    return HttpStatus.UNAUTHORIZED;
  }

  @Bean
  WebProperties.Resources resources() {
    return new WebProperties.Resources();
  }
}
