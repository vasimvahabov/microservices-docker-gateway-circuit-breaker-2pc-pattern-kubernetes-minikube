package com.example.springeurekarental.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.hibernate.annotations.Generated; 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rentals_")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rental{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="id_")
  private Integer id;
  
  @Generated 
  @Column(name ="pick_up_date_",
		  nullable = false,
		  insertable = false,
          columnDefinition="datetime default now()")
  private LocalDateTime pickUpDate;
  
  @Generated
  @Column(name="drop_off_date_",
		  nullable = false,
		  insertable = false,
		  columnDefinition="datetime default (adddate(now(),1))")
  private LocalDateTime dropOffDate;
 
  @Column(name="user_id_",nullable = false)
  private Integer userId;
  
  @Column(name="book_id_",nullable = false)
  private Integer bookId;
}