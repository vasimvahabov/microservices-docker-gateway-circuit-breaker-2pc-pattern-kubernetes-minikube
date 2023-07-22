package com.example.springeurekauser.entities;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users_")
public class User{
  @Id
  @Column(name = "id_")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @Column(name="first_name_",nullable = false,length = 100)
  private String firstName;
  
  @Column(name="last_name_",nullable = false,length = 100)
  private String lastName;  
  
  @Column(name="email_",nullable = false,length = 100,unique = true)
  private String email;
  
  
  @Column(name="password_",nullable = false,length =280)
  private String password;
}
