package com.ecomerse.ecomerse.controller;

import com.ecomerse.ecomerse.dto.AddToCartRequest;
import com.ecomerse.ecomerse.model.CartItem;
import com.ecomerse.ecomerse.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired private CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartItem> addToCart(@PathVariable Long userId, @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(userId, request));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartItems(userId));
    }
}
