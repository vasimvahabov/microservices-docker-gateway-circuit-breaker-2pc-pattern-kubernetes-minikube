//package com.example.springeurekabook.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import com.example.springeurekabook.dtos.BookDTO; 
//import java.util.Iterator; 
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.springframework.beans.factory.annotation.Autowired; 
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.util.StreamUtils;
//
//@SpringBootTest
//public class BookServiceTest {
//  
//  @Autowired 
//  private BookService _bookService;
  
//  @Test
//  public void testGetAllBooks(){ 
//    Iterator<BookDTO> iterator=this._bookService.getAllBooks().iterator();
//    Executable executable=()->{
//      StreamUtils.createStreamFromIterator(iterator)
//                 .forEach(book->{
//                    System.out.println(book.id+" "+
//                                      book.name+" "+
//                                      book.pageCount+" "+
//                                      book.status+" "+
//                                      book.authorId+" "+
//                                      book.authorFirstName+" "+
//                                      book.authorLastName+" "+ 
//                                      book.typeId+" "+
//                                      book.typeTitle);
//                 });
//    };
//    assertDoesNotThrow(executable,"Error happened during the test...");
//  }
  
//  @Test
//  public void testGetActiveBooks(){ 
//    Iterator<BookDTO> iterator=this._bookService.getActiveBooks().iterator();
//    Executable executable=()->{
//      StreamUtils.createStreamFromIterator(iterator)
//                 .forEach(book->{
//                    System.out.println(book.id+" "+
//                                      book.name+" "+
//                                      book.pageCount+" "+
//                                      book.status+" "+
//                                      book.authorId+" "+
//                                      book.authorFirstName+" "+
//                                      book.authorLastName+" "+ 
//                                      book.typeId+" "+
//                                      book.typeTitle);
//                 });
//    };
//    assertDoesNotThrow(executable,"Error happened during the test...");
//  }
//}
