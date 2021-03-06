package com.shubhankar.debita.service;

import com.shubhankar.debita.model.Category;
import com.shubhankar.debita.model.Transaction;
import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.TransactionRepository;
import com.shubhankar.debita.request.TransactionRequest;
import com.shubhankar.debita.response.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserService userService, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Transaction createTransaction(Integer userId, TransactionRequest transactionRequest) {
        User user = userService.getUser(userId);
        Category category = categoryService.getCategory(transactionRequest.getCategoryId());
        Transaction transaction = new Transaction(user, category, transactionRequest.getAmount(), transactionRequest.getNote(), getUnixTime());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction getTransaction(Integer userId, Integer transactionId) {
        return getByUserIdAndTransactionId(userId, transactionId);
    }

    public Transaction deleteTransaction(Integer userId, Integer transactionId) {
        Transaction transaction = getTransaction(userId, transactionId);
        transactionRepository.delete(transaction);
        return transaction;
    }

    public Transaction updateTransaction(Integer userId, Integer transactionId, TransactionRequest transactionRequest) {
        Transaction transaction = getTransaction(userId, transactionId);
        Category category = categoryService.getCategory(transactionRequest.getCategoryId());

        transaction.setTransactionDate(getUnixTime());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setNote(transactionRequest.getNote());
        transaction.setCategory(category);

        transactionRepository.save(transaction);

        return transaction;
    }

    private Long getUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public Map<String, Object> getAllTransactions(Integer userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> fetchedTransactions = transactionRepository.findByUserId(userId, pageable);
        Double total = (double) 0;

        List<TransactionResponse> transactions = new ArrayList<>();
        for(Transaction transaction : fetchedTransactions.getContent()) {
            transactions.add(new TransactionResponse(transaction));
            total += transaction.getAmount();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("transactions", transactions);
        result.put("totalItems", fetchedTransactions.getTotalElements());
        result.put("totalPages", fetchedTransactions.getTotalPages());
        result.put("currentPage", fetchedTransactions.getNumber());
        result.put("totalAmount", total);

        return result;
    }

    private Transaction getByUserIdAndTransactionId(Integer userId, Integer transactionId) {
        Transaction transaction = transactionRepository.findByIdAndUserId(transactionId, userId);

        if(transaction == null)
            throw new NoSuchElementException("Transaction not found");

        return transaction;
    }
}
