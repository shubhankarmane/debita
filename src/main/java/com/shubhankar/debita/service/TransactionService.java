package com.shubhankar.debita.service;

import com.shubhankar.debita.model.Category;
import com.shubhankar.debita.model.Transaction;
import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.TransactionRepository;
import com.shubhankar.debita.request.TransactionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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

    public Transaction createTransaction(TransactionRequest transactionRequest) {
        User user = userService.getUser(transactionRequest.getUserId());
        Category category = categoryService.getCategory(transactionRequest.getCategoryId());
        Transaction transaction = new Transaction(user, category, transactionRequest.getAmount(), transactionRequest.getNote(), getUnixTime());
        transaction = transactionRepository.save(transaction);
        categoryService.updateCategoryTotal(transactionRequest.getAmount(), category);
        return transaction;
    }

    private Long getUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }
}
