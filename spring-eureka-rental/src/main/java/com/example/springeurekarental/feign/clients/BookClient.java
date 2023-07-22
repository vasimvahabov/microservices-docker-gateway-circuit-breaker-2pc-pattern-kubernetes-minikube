package com.example.springeurekarental.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import com.example.springeurekarental.controllers.FallbackController;
import feign.Feign;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
  
@FeignClient(name="book-service",
             fallback = FallbackController.class,
             configuration =BookClient.FeignConfiguration.class)
public interface BookClient{
  @PutMapping("/book/change-status/{id}")
  public ResponseEntity<Void> changeBookStatus(@PathVariable("id") int id);
  
  public class FeignConfiguration{
	 
	private final CircuitBreakerRegistry _registry;
	 
	public FeignConfiguration(CircuitBreakerRegistry registry){
      this._registry=registry;
	}
	
	@Bean
	private Feign.Builder feignBuilder(){
      CircuitBreaker circuitBreaker=this._registry.circuitBreaker("book-service");
      FeignDecorators decorators=FeignDecorators.builder()
    		  									  .withCircuitBreaker(circuitBreaker)
    		  									  .withFallbackFactory(FallbackController::new)
    		  									.build();
      return Resilience4jFeign.builder(decorators);
    }
  }
}
