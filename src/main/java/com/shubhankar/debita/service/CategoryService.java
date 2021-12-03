package com.shubhankar.debita.service;

import com.shubhankar.debita.model.Category;
import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.CategoryRepository;
import com.shubhankar.debita.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(UserService userService, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryRequest categoryRequest) {
        User user = userService.getUser(categoryRequest.getUserId());
        Category category = new Category(user, categoryRequest.getTitle(), categoryRequest.getDescription(), (double) 0);
        category = categoryRepository.save(category);
        return category;
    }

    public Category getCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    public void updateCategoryTotal(Double amount, Category category) {
        category.increaseAmountBy(amount);
        categoryRepository.save(category);
    }
}
