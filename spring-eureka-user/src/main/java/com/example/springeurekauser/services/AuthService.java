package com.example.springeurekauser.services;

import java.util.Base64;
import java.util.Date;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT; 
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.springeurekauser.dtos.UserDTO;

import jakarta.annotation.PostConstruct;
 
@Component
public class AuthService {
	
  private String _secretKey; 
  private final String _issuer;
  private final UserService _userService;
		  
  public AuthService(UserService userService) {
    this._secretKey="secret-key-for-jwt";
    this._issuer="AuthService";
    this._userService=userService;
  }
  
  @PostConstruct
  public void init(){ 
    this._secretKey=Base64.getEncoder().encodeToString(this._secretKey.getBytes());
  }
   
  public String generateToken(String email){  
	
    Date now=new Date();
    Date expiration=new Date(now.getTime()+600000);
       
    return JWT.create()
    		    .withClaim("email", email)
    		    .withIssuedAt(now) 
    		    .withIssuer(this._issuer)
    		    .withExpiresAt(expiration)
    		    .sign(Algorithm.HMAC256(this._secretKey));
  }
  
  public UserDTO validateToken(String token) {
		System.out.println(token); 
	JWTVerifier verifier=JWT.require(Algorithm.HMAC256(this._secretKey))
							.withIssuer(this._issuer)
							.build();
	DecodedJWT decoded;
	try {
		decoded=verifier.verify(token);	
		System.out.println(token);
		System.out.println(decoded.getClaims().get("email"));
		
	    String email=decoded.getClaim("email").asString();
	    System.out.println(email);

	    UserDTO dto=this._userService.getUserByEmail(email);
	    return dto;
	}catch(JWTVerificationException  exception) {
		throw new JWTVerificationException(exception.getMessage());
	}
  }
}
