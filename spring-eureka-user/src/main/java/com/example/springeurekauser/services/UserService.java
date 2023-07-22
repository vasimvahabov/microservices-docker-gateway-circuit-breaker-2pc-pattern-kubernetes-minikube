package com.example.springeurekauser.services;

import com.example.springeurekauser.dtos.UserDTO;
import com.example.springeurekauser.entities.User;
import com.example.springeurekauser.repositories.UserRepository;
import com.example.springeurekauser.specifications.UserSpecifications;
import jakarta.persistence.EntityNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;  
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
  
  private final UserRepository _userRepository;
  private final UserSpecifications _userSpecifications;
  
  public UserService(UserRepository userRepository,UserSpecifications userSpecifications){
	this._userRepository=userRepository;
	this._userSpecifications=userSpecifications; 
  }
  
  public UserDTO logIn(UserDTO dto){
    User user=this._userSpecifications.logIn(dto);
    if(user==null) { 
      throw new EntityNotFoundException("Wrong login details...");
    }   
    dto.firstName=user.getFirstName();
    dto.lastName=user.getLastName();
    dto.id=user.getId();   
    return dto;
  }
  
  public UserDTO getUserByEmail(String email){
    User user=this._userSpecifications.getUserByEmail(email);
    if(user==null) {
      String msg=String.format("User not found by %s email...",email);
      throw new EntityNotFoundException(msg);
    }      
    UserDTO dto=new UserDTO(user.getId(),user.getFirstName(),user.getLastName(), user.getEmail(),null);
    return dto;
  }
	 
  public List<UserDTO> getAllUsers(){
    Iterator<User> iterator=this._userRepository.findAll().iterator();
    List<UserDTO> dtos=StreamUtils.createStreamFromIterator(iterator)
                                   .map(user->new UserDTO(user.getId(),
                                                           user.getFirstName(),
                                                           user.getLastName(),
                                                           user.getEmail(),
                                                           null)
                                   ).collect(Collectors.toList());
    log.info("getAllUsers() function executed");
    return dtos;
  }
}
