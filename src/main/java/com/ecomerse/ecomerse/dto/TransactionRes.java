package com.ecomerse.ecomerse.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TransactionRes {
    private String noInvoice;
    private LocalDateTime transactionDate;
    private String status;
    private BigDecimal totalAmount;
    private List<TransactionDetailRes> details;
}