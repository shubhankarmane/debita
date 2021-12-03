package com.shubhankar.debita.model;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    private User user;
    private String title;
    private String description;
    private Double totalExpense;

    public Category() {
    }

    public Category(User user, String title, String description, Double totalExpense) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
    }

    public void increaseAmountBy(Double amount) {
        this.totalExpense += amount;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }
}
