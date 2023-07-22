package com.example.springeurekarental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; 
 
@SpringBootApplication
@EnableFeignClients
public class SpringEurekaRentalApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringEurekaRentalApplication.class, args);
  } 
}
