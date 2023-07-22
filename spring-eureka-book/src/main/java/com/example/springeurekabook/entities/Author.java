package com.example.springeurekabook.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="authors_")
public class Author{
  @Id
  @Column(name="id_")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name="first_name_",nullable = false,length = 100)
  private String firstName;
  
  @Column(name="last_name_",nullable = false,length = 100)
  private String lastName;
  
  @OneToMany(cascade = CascadeType.REMOVE,
               fetch = FetchType.LAZY,
               mappedBy ="author",
               targetEntity = Book.class)
  private List<Book> books;
}