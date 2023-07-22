package com.example.springeurekarental.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.springeurekarental.controllers.FallbackController;
import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;

@FeignClient(name = "payment-service",
		     fallback = FallbackController.class,
             configuration=PaymentClient.FeignConfiguration.class)
public interface PaymentClient{

  @PostMapping("/payment/do-payment")
  public ResponseEntity<Void> doPayment();
  
  public class FeignConfiguration{ 
   
	private final CircuitBreakerRegistry _circuitBreakerRegistry;
	  
	public FeignConfiguration(CircuitBreakerRegistry circuitBreakerRegistry) {
      this._circuitBreakerRegistry=circuitBreakerRegistry;
    }
	
	@Bean
	private Feign.Builder feignBuilder(){
      CircuitBreaker circuitBreaker=this._circuitBreakerRegistry.circuitBreaker("payment-service");
      FeignDecorators decorators=FeignDecorators.builder()
    		  									    .withCircuitBreaker(circuitBreaker)
    		  									    .withFallbackFactory(FallbackController::new)
    		  									.build();
	  return Resilience4jFeign.builder(decorators);
	}
  }
}
