package com.ecomerse.ecomerse.service;

import com.ecomerse.ecomerse.dto.AddToCartRequest;
import com.ecomerse.ecomerse.model.CartItem;
import com.ecomerse.ecomerse.model.Product;
import com.ecomerse.ecomerse.model.User;
import com.ecomerse.ecomerse.repository.CartItemRepo;
import com.ecomerse.ecomerse.repository.ProductRepo;
import com.ecomerse.ecomerse.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {
    @Autowired private CartItemRepo cartItemRepository;
    @Autowired private UserRepo userRepository;
    @Autowired private ProductRepo productRepository;

    public CartItem addToCart(Long userId, AddToCartRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getStock() < request.getQuantity()) { throw new RuntimeException("Stock not sufficient"); }

        CartItem cartItem = cartItemRepository.findByUser_IdAndProduct_Id(userId, request.getProductId())
                .map(item -> {
                    item.setQuantity(item.getQuantity() + request.getQuantity());
                    return item;
                })
                .orElseGet(() -> {
                    CartItem newItem = new CartItem();
                    newItem.setUser(user);
                    newItem.setProduct(product);
                    newItem.setQuantity(request.getQuantity());
                    newItem.setAddedAt(LocalDateTime.now());
                    return newItem;
                });
        return cartItemRepository.save(cartItem);
    }
    public List<CartItem> getCartItems(Long userId) { return cartItemRepository.findByUser_Id(userId); }
}
