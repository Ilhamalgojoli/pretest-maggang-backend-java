package com.ecomerse.ecomerse.repository;

import com.ecomerse.ecomerse.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUser_IdAndProduct_Id(Long userId, Long productId);
    List<CartItem> findByUser_Id(Long userId);
}
