package com.example.springeurekauser.repositories;

import com.example.springeurekauser.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>,
										JpaSpecificationExecutor<User>{}
