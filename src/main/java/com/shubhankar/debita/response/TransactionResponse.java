package com.shubhankar.debita.response;

import com.shubhankar.debita.model.Transaction;

public class TransactionResponse {
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private Double amount;
    private String note;
    private Long transactionDate;

    public TransactionResponse(Integer id, Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
    }

    public TransactionResponse(Transaction transaction) {
        this.id = transaction.getId();
        this.userId = transaction.getUser().getId();
        this.categoryId = transaction.getCategory().getId();
        this.amount = transaction.getAmount();
        this.note = transaction.getNote();
        this.transactionDate = transaction.getTransactionDate();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Long transactionDate) {
        this.transactionDate = transactionDate;
    }
}
