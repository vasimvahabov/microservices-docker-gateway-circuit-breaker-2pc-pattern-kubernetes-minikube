package com.example.springeurekabook.controllers;

import com.example.springeurekabook.dtos.BookDTO; 
import com.example.springeurekabook.models.BookModel;
import com.example.springeurekabook.services.BookService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors; 
import org.springframework.core.env.Environment;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController { 
    
  private final BookService _bookService;  
  private final Environment _env; 
  
  public BookController(BookService bookService,Environment environment){
    this._bookService=bookService;
    this._env=environment;
  }
    
  @GetMapping("/list/all")
  public ResponseEntity<HashMap<String,Object>> getAllBooks(){ 
    Iterator<BookDTO> iterator=this._bookService.getAllBooks().iterator();
    List<BookModel> models=StreamUtils.createStreamFromIterator(iterator)
                                      .map(dto-> BookModel.builder()
                                                             .id(dto.id)
                                                             .name(dto.name)
                                                             .pageCount(dto.pageCount)
                                                             .authorId(dto.authorId)
                                                             .status(dto.status)
                                                             .authorFirstName(dto.authorFirstName)
                                                             .authorLastName(dto.authorLastName)
			                                                 .typeId(dto.typeId)
			                                                 .typeTitle(dto.typeTitle)
			                                              .build()
                                      ).collect(Collectors.toList()); 
   
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",models);
    
    return ResponseEntity.ok(hashMap);
  }
  
  @GetMapping("/list/active")
  public ResponseEntity<HashMap<String,Object>> getActiveBooks(){ 
    Iterator<BookDTO> iterator=this._bookService.getActiveBooks().iterator();
    List<BookModel> models=StreamUtils.createStreamFromIterator(iterator)
                                      .map(dto-> BookModel.builder()
                                                             .id(dto.id)
                                                             .name(dto.name)
                                                             .pageCount(dto.pageCount)
                                                             .authorId(dto.authorId)
                                                             .status(dto.status)
                                                             .authorFirstName(dto.authorFirstName)
                                                             .authorLastName(dto.authorLastName)
			                                                 .typeId(dto.typeId)
			                                                 .typeTitle(dto.typeTitle)
			                                              .build()
                                      ).collect(Collectors.toList()); 
   
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",models);
    
    return ResponseEntity.ok(hashMap);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<HashMap<String,Object>> getAllBooks(@PathVariable int id){ 
    BookDTO dto=this._bookService.getBookById(id);
    BookModel model=BookModel.builder()
					    		.id(dto.id)
					    		.name(dto.name)
					    		.pageCount(dto.pageCount)
					    		.authorId(dto.authorId)
					    		.status(dto.status)
					    		.authorFirstName(dto.authorFirstName)
					    		.authorLastName(dto.authorLastName)
					    		.typeId(dto.typeId)
					    		.typeTitle(dto.typeTitle)
					    	 .build();
   
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",model);
    
    return ResponseEntity.ok(hashMap);
  }
  
  @PostMapping("/add")
  public ResponseEntity<HashMap<String,Object>> addBook(@RequestBody BookModel model){ 
	System.out.println(model);
    BookDTO dto=new BookDTO(null,
    		                model.name,
    		                model.pageCount,
    		                null,
    		                model.authorId,
    		                null,
    		                null,
    		                model.typeId,
    		                null);
    dto=this._bookService.addBook(dto);
    model.id=dto.id;
    model.authorFirstName=dto.authorFirstName;
    model.authorLastName=dto.authorLastName;
    model.status=dto.status;
    model.typeTitle=dto.typeTitle; 
    
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",model);
    
    return ResponseEntity.ok(hashMap);
  }
  
  @PutMapping("/update")
  public ResponseEntity<HashMap<String,Object>> updateBook(@RequestBody BookModel model){ 
    BookDTO dto=new BookDTO(model.id,
    		                model.name,
    		                model.pageCount,
    		                null,
    		                model.authorId,
    		                null,
    		                null,
    		                model.typeId,
    		                null);
    dto=this._bookService.updateBook(dto);
    
    model.id=dto.id;
    model.authorFirstName=dto.authorFirstName;
    model.authorLastName=dto.authorLastName;
    model.status=dto.status;
    model.typeTitle=dto.typeTitle; 
    
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",model);
    
    return ResponseEntity.ok(hashMap);
  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<HashMap<String,Object>> deleteBook(@PathVariable int id){ 
    BookDTO dto=this._bookService.deleteBook(id);
    BookModel model=BookModel.builder()
					            .id(dto.id)
					            .name(dto.name)
					            .pageCount(dto.pageCount)
					            .authorId(dto.authorId)
					            .status(dto.status)
					            .authorFirstName(dto.authorFirstName)
					            .authorLastName(dto.authorLastName)
					            .typeId(dto.typeId)
					            .typeTitle(dto.typeTitle)
				             .build();
   
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",model);
    
    return ResponseEntity.ok(hashMap);
  }
  
  @PutMapping("/change-status/{id}")
  public ResponseEntity<HashMap<String,Object>> changeBookStatus(@PathVariable int id){ 
    BookDTO dto=this._bookService.changeBookStatus(id);
    BookModel model=BookModel.builder()
					            .id(dto.id)
					            .name(dto.name)
					            .pageCount(dto.pageCount)
					            .authorId(dto.authorId)
					            .status(dto.status)
					            .authorFirstName(dto.authorFirstName)
					            .authorLastName(dto.authorLastName)
					            .typeId(dto.typeId)
					            .typeTitle(dto.typeTitle)
				             .build();
   
    HashMap<String,Object> hashMap=new HashMap<>(2);
    hashMap.put("port",_env.getProperty("local.server.port") );
    hashMap.put("body",model);
    
    return ResponseEntity.ok(hashMap);
  }
}