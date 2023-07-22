package com.example.springeurekagateway.config; 

import org.springframework.stereotype.Component;  
import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.boot.web.error.ErrorAttributeOptions; 
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.web.reactive.function.server.ServerRequest;   
import lombok.extern.slf4j.Slf4j;  
import org.springframework.http.HttpStatus; 

@Component
@Slf4j 
public class ExtendedErrorAttributes extends DefaultErrorAttributes { 
		  
  @Override
  public Map<String, Object> getErrorAttributes(ServerRequest request,ErrorAttributeOptions options) {
      	 
	  LinkedHashMap<String,Object> attributes = new LinkedHashMap<String,Object>();
      SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  String key="org.springframework.boot.web.reactive.error.DefaultErrorAttributes.ERROR";
	  String msg=request.attributes().get(key).toString();
      
      attributes.put("status", HttpStatus.BAD_GATEWAY.value());
      attributes.put("message",msg);
      attributes.put("date",simpleDateFormat.format(new Date()));
      
      log.error(attributes.get("status").toString()+" "+
    		  	attributes.get("message").toString()+" "+
    		  	attributes.get("date").toString());
      return attributes;
  }
}