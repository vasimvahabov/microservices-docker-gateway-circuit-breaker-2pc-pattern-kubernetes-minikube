package com.example.springeurekarental.repositories;

import com.example.springeurekarental.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository; 

public interface RentalRepository extends JpaRepository<Rental,Object>{}