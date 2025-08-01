package com.ecomerse.ecomerse.repository;

import com.ecomerse.ecomerse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {}
