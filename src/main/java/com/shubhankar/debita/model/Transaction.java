package com.shubhankar.debita.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Category category;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private String note;
    @Column(nullable = false)
    private Long transactionDate;

    public Transaction() {
    }

    public Transaction(User user, Category category, Double amount, String note, Long transactionDate) {
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.note = note;
        this.transactionDate = transactionDate;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
