package com.shubhankar.debita.response;

import com.shubhankar.debita.model.Category;

public class CategoryResponse {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Double totalExpense;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.userId = category.getUser().getId();
        this.title = category.getTitle();
        this.description = category.getDescription();
        this.totalExpense = category.getTotalExpense();
    }

    public CategoryResponse(Integer id, Integer userId, String title, String description, Double totalExpense) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.totalExpense = totalExpense;
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
