package com.example.springeurekabook.entities;
 
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books_")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id_")
  private Integer id;
  
  @Column(name="name_",nullable = false,length = 100)
  private String name;
  
  @Column(name="page_count_",nullable = false)
  private Integer pageCount;

  @ColumnDefault(value = "true")
  @Column(name="status_",nullable = false,insertable = false)
  private Boolean status; 
  
  @ManyToOne(fetch = FetchType.EAGER,optional =false,targetEntity =Author.class)
  @JoinColumn(name="author_id_",nullable = false,referencedColumnName = "id_")
  private Author author;
  
  @ManyToOne(fetch = FetchType.EAGER,optional = false,targetEntity = Type.class)
  @JoinColumn(name="type_id_",nullable = false,referencedColumnName = "id_")
  private Type type;
}