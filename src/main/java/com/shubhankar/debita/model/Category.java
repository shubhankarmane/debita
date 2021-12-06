package com.shubhankar.debita.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(optional = false)
    private User user;
    @Column(unique = true, nullable = false)
    private String title;
    private String description;

    public Category() {
    }

    public Category(User user, String title, String description, Double totalExpense) {
        this.user = user;
        this.title = title;
        this.description = description;
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
}
