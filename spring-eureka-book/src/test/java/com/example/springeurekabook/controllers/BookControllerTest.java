//package com.example.springeurekabook.controllers;
// 
//import com.example.springeurekabook.models.BookModel;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest; 
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class BookControllerTest {
//
//  @Autowired
//  private MockMvc _mockMvc;
//  
//  @Autowired
//  private ObjectMapper _objectMapper;
//  
//  @Test
//  public void testGetAllBooks(){
//    Executable executable=()->{
//      String uri="/books/list/all";
//      MvcResult result=this._mockMvc.perform(get(uri))
//                                    .andDo(print())
//                                    .andExpect(status().isOk())
//                                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                                    .andReturn();
//      String responseBody=result.getResponse().getContentAsString();
//      HashMap<String,Object> responseHashMap=this._objectMapper.readValue(responseBody,HashMap.class);
//      List<BookModel> models=this._objectMapper.readValue(responseHashMap.get("body").toString(),List.class);
//      for(var item:models){
//        System.out.println(item.id+" "+
//                         item.name+" "+
//                         item.pageCount+" "+
//                         item.status+
//                         item.authorId+" "+
//                         item.authorFirstName+
//                         item.authorLastName+                         
//                         item.typeId+" "+
//                         item.typeTitle);
//      }
//    };
//    assertDoesNotThrow(executable,"Error happened during test...");
//  }
//    
//}
