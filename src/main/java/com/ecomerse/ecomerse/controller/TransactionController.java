package com.ecomerse.ecomerse.controller;

import com.ecomerse.ecomerse.dto.TransactionRes;
import com.ecomerse.ecomerse.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/checkout/{userId}")
    public ResponseEntity<TransactionRes> checkout(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.checkout(userId));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<TransactionRes>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(userId));
    }
}