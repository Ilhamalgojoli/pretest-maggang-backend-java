package com.ecomerse.ecomerse.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime transactionDate;
    private BigDecimal totalAmount;
    private String status;
    private String noInvoice;
    private String senderInfo;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<DetailTransaction> detailTransactions;
}