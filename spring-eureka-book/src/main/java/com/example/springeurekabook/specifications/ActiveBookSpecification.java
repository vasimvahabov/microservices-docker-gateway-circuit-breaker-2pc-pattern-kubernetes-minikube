package com.example.springeurekabook.specifications;

import org.springframework.data.jpa.domain.Specification;
import com.example.springeurekabook.entities.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root; 

public class ActiveBookSpecification implements Specification<Book>{

  private static final long serialVersionUID = 1L;

  @Override
  public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.equal(root.get("status"),true);
  }
  
}
