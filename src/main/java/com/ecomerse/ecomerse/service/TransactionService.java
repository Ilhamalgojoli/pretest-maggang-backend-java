package com.ecomerse.ecomerse.service;

import com.ecomerse.ecomerse.model.*;
import com.ecomerse.ecomerse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired private TransactionRepo transactionRepository;
    @Autowired private CartItemRepo cartItemRepository;
    @Autowired private UserRepo userRepository;
    @Autowired private ProductRepo productRepository;

    @Transactional
    public Transaction checkout(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(userId);
        if (cartItems.isEmpty()) { throw new RuntimeException("Cart is empty"); }

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("SUCCESS");
        transaction.setNoInvoice("INV-" + UUID.randomUUID().toString());
        List<DetailTransaction> detailList = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) { throw new RuntimeException("Stock for " + product.getName() + " is not sufficient"); }

            DetailTransaction detail = new DetailTransaction();
            detail.setTransaction(transaction);
            detail.setProduct(product);
            detail.setQuantity(item.getQuantity());
            detail.setPriceAtPurchase(product.getPrice());
            detailList.add(detail);

            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }
        transaction.setTotalAmount(totalAmount);
        transaction.setDetailTransactions(detailList);
        Transaction savedTransaction = transactionRepository.save(transaction);
        cartItemRepository.deleteAll(cartItems);
        return savedTransaction;
    }
    public List<Transaction> getTransactionHistory(Long userId) { return transactionRepository.findByUser_Id(userId); }
}
