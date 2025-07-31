package com.ecomerse.ecomerse.controller;

import com.ecomerse.ecomerse.model.Transaction;
import com.ecomerse.ecomerse.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired private TransactionService transactionService;

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Transaction> checkout(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(transactionService.checkout(userId));
        } catch (RuntimeException e) {
            // Sebaiknya gunakan @ControllerAdvice untuk error handling yang lebih baik
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(userId));
    }
}
