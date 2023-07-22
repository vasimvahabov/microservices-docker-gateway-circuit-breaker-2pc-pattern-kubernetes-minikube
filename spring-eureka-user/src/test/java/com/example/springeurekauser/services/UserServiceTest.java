package com.example.springeurekauser.services;

import static org.junit.jupiter.api.Assertions.*;
import com.example.springeurekauser.dtos.UserDTO;
import java.util.Iterator; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.StreamUtils;

@SpringBootTest
public class UserServiceTest {
  
  @Autowired
  private UserService _userService;
    
  @Test
  public void testGetAllUsers(){
    Iterator<UserDTO> iterator=this._userService.getAllUsers().iterator();
    Executable executable=()->{
      StreamUtils.createStreamFromIterator(iterator)
                .forEach(user-> System.out.println(user.id+" "+
                                                 user.firstName+" "+
                                                 user.lastName+" "+
                                                 user.email+" "+
                                                 user.password));
    };
    assertDoesNotThrow(executable,"Error happened during test...");
  }
    
}
