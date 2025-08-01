package com.ecomerse.ecomerse.repository;

import com.ecomerse.ecomerse.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser_Id(Long userId);
}
