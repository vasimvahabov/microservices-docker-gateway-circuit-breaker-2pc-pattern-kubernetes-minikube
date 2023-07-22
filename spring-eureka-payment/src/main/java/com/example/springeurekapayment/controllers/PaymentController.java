package com.example.springeurekapayment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
  @PostMapping("/do-payment")
  public ResponseEntity<Void> doPayment(){
	log.info("doPayment() method in PaymentController executed....");
    return ResponseEntity.ok().build();
  }
}
