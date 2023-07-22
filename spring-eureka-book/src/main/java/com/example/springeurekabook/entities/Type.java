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
@Table(name = "types_")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_")
  private Integer id;
  
  @Column(name = "title_",nullable = false,length = 100)
  private String title;  
  
  @OneToMany(cascade = CascadeType.REMOVE,
               fetch =FetchType.LAZY,
               mappedBy ="type",
               targetEntity=Book.class)
  public List<Book> books; 
}