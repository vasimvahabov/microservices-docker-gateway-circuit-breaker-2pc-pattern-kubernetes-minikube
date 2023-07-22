package com.example.springeurekabook.services;

import static com.example.springeurekabook.specifications.BookSpecifications.isActiveBook;
import static org.springframework.data.jpa.domain.Specification.where;
import com.example.springeurekabook.dtos.BookDTO;
import com.example.springeurekabook.entities.Author;
import com.example.springeurekabook.entities.Book;
import com.example.springeurekabook.entities.Type;
import com.example.springeurekabook.repositories.BookRepository; 
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.StreamUtils;
import org.springframework.stereotype.Service;

@Service
public class BookService {
   
  @Autowired
  private BookRepository _bookRepository;
   
  public List<BookDTO> getAllBooks(){
    Iterator<Book> iterator=this._bookRepository.findAll().iterator();
    List<BookDTO> dtos=StreamUtils.createStreamFromIterator(iterator)
                                  .map(book-> new BookDTO(book.getId(),
                                                          book.getName(),
                                                          book.getPageCount(),
                                                          book.getStatus(),
                                                          book.getAuthor().getId(), 
                                                          book.getAuthor().getFirstName(),
                                                          book.getAuthor().getLastName(),
                                                          book.getType().getId(),
                                                          book.getType().getTitle())
                                  ).collect(Collectors.toList());
    return dtos;
  }
  
  public List<BookDTO> getActiveBooks(){
	Iterator<Book> iterator=this._bookRepository.findAll(where(isActiveBook())).iterator(); 
	List<BookDTO> dtos=StreamUtils.createStreamFromIterator(iterator)
	                                  .map(book-> new BookDTO(book.getId(),
	                                                          book.getName(),
	                                                          book.getPageCount(),
	                                                          book.getStatus(),
	                                                          book.getAuthor().getId(), 
	                                                          book.getAuthor().getFirstName(),
	                                                          book.getAuthor().getLastName(),
	                                                          book.getType().getId(),
	                                                          book.getType().getTitle())
	                                  ).collect(Collectors.toList());
	    return dtos;
  }
  
  public BookDTO getBookById(int id){
    Book book=this._bookRepository.findById(id).orElse(null);
    BookDTO dto=new BookDTO(book.getId(),
				            book.getName(),
				            book.getPageCount(),
				            book.getStatus(),
				            book.getAuthor().getId(), 
				            book.getAuthor().getFirstName(),
				            book.getAuthor().getLastName(),
				            book.getType().getId(),
				            book.getType().getTitle());
    return dto;
  } 
  
  public BookDTO addBook(BookDTO dto){
    Author author=new Author(dto.authorId,null,null,null);
    Type type=new Type(dto.typeId,null,null);
    Book book=new Book(dto.id,dto.name,dto.pageCount,null,author,type);
    book=this._bookRepository.save(book);
    book=this._bookRepository.findById(book.getId()).orElse(null);
    		
    dto.id=book.getId();
    dto.authorFirstName=book.getAuthor().getFirstName();
    dto.authorLastName=book.getAuthor().getLastName();
    dto.typeTitle=book.getType().getTitle();
    dto.status=book.getStatus();
    
    return dto;
  }
  
  public BookDTO updateBook(BookDTO dto){ 
	Author author=new Author(dto.authorId,null,null,null);
	Type type=new Type(dto.typeId,null,null);
	Book book=this._bookRepository.findById(dto.id).orElse(null); 
	
	book.setName(dto.name);
	book.setPageCount(dto.pageCount);
	book.setAuthor(author);
	book.setType(type); 
	book=this._bookRepository.save(book);
	
	
	dto.authorFirstName=book.getAuthor().getFirstName();
	dto.authorLastName=book.getAuthor().getLastName();
	dto.typeTitle=book.getType().getTitle();
	dto.status=book.getStatus();
	return dto;
  }
  
  public BookDTO deleteBook(int id){  
    Book book=this._bookRepository.findById(id).orElse(null); 
    BookDTO dto=new BookDTO(book.getId(),
				            book.getName(),
				            book.getPageCount(),
				            book.getStatus(),
				            book.getAuthor().getId(), 
				            book.getAuthor().getFirstName(),
				            book.getAuthor().getLastName(),
				            book.getType().getId(),
				            book.getType().getTitle());
    this._bookRepository.deleteById(id);
	return dto;
  }
  
  public BookDTO changeBookStatus(int id){  
    Book book=this._bookRepository.findById(id).orElse(null); 
    book.setStatus(!book.getStatus());
    book=this._bookRepository.save(book);
    
	BookDTO dto=new BookDTO(book.getId(),
							book.getName(),
					        book.getPageCount(),
					        book.getStatus(),
					        book.getAuthor().getId(), 
					        book.getAuthor().getFirstName(),
					        book.getAuthor().getLastName(),
					        book.getType().getId(),
					        book.getType().getTitle());
	return dto;
  }
}