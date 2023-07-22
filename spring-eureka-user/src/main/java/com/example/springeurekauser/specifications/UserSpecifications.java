package com.example.springeurekauser.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import static org.springframework.data.jpa.domain.Specification.where;
import com.example.springeurekauser.dtos.UserDTO;
import com.example.springeurekauser.entities.User;
import com.example.springeurekauser.repositories.UserRepository; 

@Component
public class UserSpecifications{
  private final UserRepository _userRepository;
  
  public UserSpecifications(UserRepository userRepository) {
    this._userRepository=userRepository;
  }
  
  public User logIn(UserDTO dto){
    return this._userRepository.findOne(where(logInSpecification(dto))).orElse(null);
  }  
  
  public User getUserByEmail(String email){
    return this._userRepository.findOne(where(userByEmail(email))).orElse(null);
  }
  
  private Specification<User> logInSpecification(UserDTO dto){
    return (root, query, criteriaBuilder) -> query.where(
    		                                          criteriaBuilder.equal(root.get("email"),dto.email),
    										          criteriaBuilder.equal(root.get("password"),dto.password))
    										      .getRestriction();
  }
  
  private Specification<User> userByEmail(String email){
    return (root, query, criteriaBuilder) -> query.where(criteriaBuilder.equal(root.get("email"),email))
    											  .getRestriction(); 		
  }
}
