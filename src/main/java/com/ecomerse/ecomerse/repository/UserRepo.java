package com.ecomerse.ecomerse.repository;

import com.ecomerse.ecomerse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {}
