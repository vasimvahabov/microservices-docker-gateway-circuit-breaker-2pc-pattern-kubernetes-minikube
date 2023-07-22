package com.example.springeurekauser.controllers;

import com.example.springeurekauser.dtos.UserDTO;
import com.example.springeurekauser.models.UserModel;
import com.example.springeurekauser.services.AuthService;
import com.example.springeurekauser.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.core.env.Environment;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService _userService;
  private final Environment _env;
  private final AuthService _authService;
  private final ObjectMapper _objectMapper;
  
  public UserController(UserService userService,Environment environment,AuthService authService,ObjectMapper objectMapper){
    this._userService=userService;
    this._env=environment;
    this._authService=authService;
    this._objectMapper=objectMapper;
  }
  
  @GetMapping("/list/all")
  public ResponseEntity<HashMap<String,Object>> getAllUsers(){
    Iterator<UserDTO> iterator=this._userService.getAllUsers().iterator();
    List<UserModel> models=StreamUtils.createStreamFromIterator(iterator)
                                       .map(user->UserModel.builder()
                                                               .id(user.id)
                                                               .firstName(user.firstName)
                                                               .lastName(user.lastName)
                                                               .email(user.email)
                                                               .password(null)
                                                            .build()
                                       
                                       
                                       ).collect(Collectors.toList());
      
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port", this._env.getProperty("local.server.port"));
    hashMap.put("body", models);
    return ResponseEntity.ok(hashMap);
  }
  
  @PostMapping("/log-in")
  public ResponseEntity<String> logIn(@RequestBody UserModel model) throws JsonProcessingException{ 
	UserDTO dto=new UserDTO(null, null, null,model.email, model.password);
    dto=this._userService.logIn(dto);
    String token=this._authService.generateToken(dto.email);
    token=this._objectMapper.writeValueAsString(token);
    return ResponseEntity.ok(token);
  }
  
  @PostMapping("/validate-token")
  public ResponseEntity<UserModel> validateToken(@RequestHeader (name="Authorization") String token){ 
	log.warn(token);
	UserDTO dto=this._authService.validateToken(token.split(" ")[1]);
	UserModel model=UserModel.builder()
								  .id(dto.id)
								  .firstName(dto.firstName)
								  .lastName(dto.lastName)
								  .email(dto.email)
								  .password(dto.password)
						     .build();
    return ResponseEntity.ok(model);
  }
}
