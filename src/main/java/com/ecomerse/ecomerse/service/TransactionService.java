package com.ecomerse.ecomerse.service;

import com.ecomerse.ecomerse.dto.TransactionDetailRes;
import com.ecomerse.ecomerse.dto.TransactionRes;
import com.ecomerse.ecomerse.model.*;
import com.ecomerse.ecomerse.repository.CartItemRepo;
import com.ecomerse.ecomerse.repository.ProductRepo;
import com.ecomerse.ecomerse.repository.TransactionRepo;
import com.ecomerse.ecomerse.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepository;
    private final CartItemRepo cartItemRepository;
    private final UserRepo userRepository;
    private final ProductRepo productRepository;

    public TransactionService(TransactionRepo transactionRepository, CartItemRepo cartItemRepository,
                              UserRepo userRepository, ProductRepo productRepository) {
        this.transactionRepository = transactionRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public TransactionRes checkout(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<CartItem> cartItems = cartItemRepository.findByUser_Id(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("SUCCESS");
        transaction.setNoInvoice("INV-" + UUID.randomUUID().toString());
        List<DetailTransaction> detailList = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Stock for " + product.getName() + " is not sufficient");
            }

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

        return convertTransactionToResponse(savedTransaction);
    }

    public List<TransactionRes> getTransactionHistory(Long userId) {
        return transactionRepository.findByUser_Id(userId).stream()
                .map(this::convertTransactionToResponse)
                .collect(Collectors.toList());
    }

    private TransactionRes convertTransactionToResponse(Transaction transaction) {
        TransactionRes response = new TransactionRes();
        response.setNoInvoice(transaction.getNoInvoice());
        response.setTransactionDate(transaction.getTransactionDate());
        response.setStatus(transaction.getStatus());
        response.setTotalAmount(transaction.getTotalAmount());

        List<TransactionDetailRes> details = transaction.getDetailTransactions().stream()
                .map(detail -> {
                    TransactionDetailRes detailResponse = new TransactionDetailRes();
                    detailResponse.setProductId(detail.getProduct().getId());
                    detailResponse.setProductName(detail.getProduct().getName());
                    detailResponse.setQuantity(detail.getQuantity());
                    detailResponse.setPriceAtPurchase(detail.getPriceAtPurchase());
                    return detailResponse;
                }).collect(Collectors.toList());

        response.setDetails(details);
        return response;
    }
}