package com.example.springeurekagateway.config;

import org.springframework.core.io.buffer.DataBuffer; 
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus; 
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component; 
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import com.example.springeurekagateway.errors.ErrorResponse;
import com.example.springeurekagateway.models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;  

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>{
  private final WebClient.Builder _webClientBuilder;
  private final ObjectMapper _objectMapper;
  
  public AuthFilter(WebClient.Builder webClientBuilder,ObjectMapper objectMapper){
	super(Config.class);
    this._webClientBuilder=webClientBuilder;
    this._objectMapper=objectMapper;
  }

  @Override
  public GatewayFilter apply(Config config) { 
    return (exchange, chain) -> {      	
      if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
        return this.onError(exchange);
    
      String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
      String[] arr=authHeader.split(" ");
      if(arr.length!=2 || !"Bearer".equals(arr[0]))
        return this.onError(exchange);
      
      String token="Bearer "+arr[1];
       
//      log.info("Token : "+token);
       	
       return this._webClientBuilder
    		      .build()
    		      .post()
    		      .uri("http://user-service/user/validate-token")
    		      .header("Authorization",token)				
    		      .retrieve() 
				  .bodyToMono(UserModel.class)    
				  .map(model->{ 
					System.out.println(model.id+" "+
									   model.firstName+" "+
									   model.lastName+" "+
									   model.email+" "+
									   model.password+" ");
			        return exchange;
				  }).flatMap(chain::filter);	 
    };
  }
  
  private Mono<Void> onError(ServerWebExchange exchange){	  
	String msg="Not found jwt token";
	int statusCode=HttpStatus.UNAUTHORIZED.value();
	
	ErrorResponse errorResponse=new ErrorResponse(statusCode,msg); 
    ServerHttpResponse response=exchange.getResponse();
    
	try{ 
	  byte[] byteArr=this._objectMapper.writeValueAsBytes(errorResponse);
	  DataBuffer buffer= exchange.getResponse().bufferFactory().wrap(byteArr);
	  return response.writeWith(Flux.just(buffer));
	}catch (JsonProcessingException e){ 
	  log.error("In onError method -> "+e);
	}  
	
	return null;
  }
  
  public static class Config {}

}
