package com.ecomerse.ecomerse.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransactionDetailRes {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal priceAtPurchase;
}