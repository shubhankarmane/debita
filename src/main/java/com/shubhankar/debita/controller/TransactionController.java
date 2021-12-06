package com.shubhankar.debita.controller;

import com.shubhankar.debita.model.Transaction;
import com.shubhankar.debita.request.TransactionRequest;
import com.shubhankar.debita.response.TransactionResponse;
import com.shubhankar.debita.service.TransactionService;
import com.shubhankar.debita.util.GetUserIdFromRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/")
    public ResponseEntity<TransactionResponse> createTransaction(
            @RequestBody TransactionRequest transactionRequest,
            HttpServletRequest request) {
        Transaction createdTransaction = transactionService.createTransaction((Integer) request.getAttribute("userId"), transactionRequest);
        return new ResponseEntity<>(new TransactionResponse(createdTransaction), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(
            @PathVariable Integer transactionId,
            HttpServletRequest request) {
        Transaction fetchedTransaction = transactionService.getTransaction(GetUserIdFromRequest.get(request), transactionId);
        return new ResponseEntity<>(new TransactionResponse(fetchedTransaction), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllTransactions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            HttpServletRequest request) {
        Map<String, Object> response = transactionService.getAllTransactions(GetUserIdFromRequest.get(request), page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Integer transactionId,
            @RequestBody TransactionRequest transactionRequest,
            HttpServletRequest request) {
        Transaction updatedTransaction = transactionService.updateTransaction(GetUserIdFromRequest.get(request), transactionId, transactionRequest);
        return new ResponseEntity<>(new TransactionResponse(updatedTransaction), HttpStatus.OK);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> deleteTransaction(
            @PathVariable Integer transactionId,
            HttpServletRequest request) {
        Transaction deletedTransaction = transactionService.deleteTransaction(GetUserIdFromRequest.get(request), transactionId);
        return new ResponseEntity<>(new TransactionResponse(deletedTransaction), HttpStatus.OK);
    }
}
