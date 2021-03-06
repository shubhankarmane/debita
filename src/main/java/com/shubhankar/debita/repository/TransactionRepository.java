package com.shubhankar.debita.repository;

import com.shubhankar.debita.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findByUserId(Integer userId, Pageable pageable);
    Transaction findByIdAndUserId(Integer transactionId, Integer userId);
    List<Transaction> findByCategoryId(Integer categoryId);
}
