package com.example.springeurekabook.specifications;

import org.springframework.data.jpa.domain.Specification;
import com.example.springeurekabook.entities.Book;

public final class BookSpecifications{
	
  public static Specification<Book> isActiveBook(){
    return new ActiveBookSpecification();
  }
  
}
