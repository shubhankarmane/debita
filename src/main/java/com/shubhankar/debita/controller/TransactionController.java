package com.shubhankar.debita.controller;

import com.shubhankar.debita.model.Transaction;
import com.shubhankar.debita.request.TransactionRequest;
import com.shubhankar.debita.response.TransactionResponse;
import com.shubhankar.debita.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction createdTransaction = transactionService.createTransaction(transactionRequest);
        return new ResponseEntity<>(new TransactionResponse(createdTransaction), HttpStatus.OK);
    }
}
