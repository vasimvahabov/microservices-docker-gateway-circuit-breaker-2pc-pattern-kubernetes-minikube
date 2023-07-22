package com.example.springeurekarental.controllers;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import com.example.springeurekarental.error.FeignException;
import com.example.springeurekarental.feign.clients.BookClient;
import com.example.springeurekarental.feign.clients.PaymentClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FallbackController implements BookClient,PaymentClient{
    
  private final Exception _exception;
	
  public FallbackController(Exception exception) {
    this._exception=exception;
  }

  @Override
  public ResponseEntity<Void> changeBookStatus(int id){
	log.error("changeBookStatus() method runned in FallbackController...");
	log.error(this._exception.getMessage());

	String uuid=UUID.randomUUID().toString();
	throw new FeignException(uuid,"Service unavailable...");
  }

	@Override
	public ResponseEntity<Void> doPayment() {
	  log.error("doPayment() method runned in FallbackController...");
	  log.error(this._exception.getMessage());

	  String uuid=UUID.randomUUID().toString();
	  throw new FeignException(uuid,"Service unavailable...");
	} 
}
