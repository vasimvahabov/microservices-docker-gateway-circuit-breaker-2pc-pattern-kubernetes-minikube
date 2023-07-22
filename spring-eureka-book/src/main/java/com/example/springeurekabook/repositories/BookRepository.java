package com.example.springeurekabook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.springeurekabook.entities.Book;

public interface BookRepository extends JpaRepository<Book,Integer>,
										JpaSpecificationExecutor<Book>{}