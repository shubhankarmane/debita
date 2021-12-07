package com.shubhankar.debita.service;

import com.shubhankar.debita.exception.DuplicateCategoryException;
import com.shubhankar.debita.model.Category;
import com.shubhankar.debita.model.Transaction;
import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.CategoryRepository;
import com.shubhankar.debita.repository.TransactionRepository;
import com.shubhankar.debita.request.CategoryRequest;
import com.shubhankar.debita.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public CategoryService(UserService userService, CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    public Category createCategory(CategoryRequest categoryRequest, Integer userId) {
        Category category = categoryRepository.findByTitleAndUserId(categoryRequest.getTitle(), userId);

        if(category != null)
            throw new DuplicateCategoryException("Category already exists");

        User user = userService.getUser(userId);
        category = new Category(user, categoryRequest.getTitle(), categoryRequest.getDescription(), (double) 0);
        category = categoryRepository.save(category);
        return category;
    }

    public Category getCategory(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);

        if(category.isEmpty())
            throw new NoSuchElementException("Category not found");

        return category.get();
    }

    public Map<String, Object> getUserCategories(Integer userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> fetchedCategories = categoryRepository.findByUserId(userId, pageable);

        List<CategoryResponse> categories = new ArrayList<>();
        for(Category category : fetchedCategories.getContent()) {
            categories.add(new CategoryResponse(category));
        }

        Map<String, Object> result = new HashMap<>();
        result.put("categories", categories);
        result.put("totalItems", fetchedCategories.getTotalElements());
        result.put("totalPages", fetchedCategories.getTotalPages());
        result.put("currentPage", fetchedCategories.getNumber());

        return result;
    }

    public Category updateCategory(Integer categoryId, Integer userId, CategoryRequest categoryRequest) {
        Category category = getCategoryByIdAndUserId(categoryId, userId);
        category.setTitle(categoryRequest.getTitle());
        category.setDescription(categoryRequest.getDescription());

        categoryRepository.save(category);

        return category;
    }

    public Category deleteCategory(Integer categoryId, Integer userId) {
        Category category = getCategoryByIdAndUserId(categoryId, userId);
        List<Transaction> transactions = transactionRepository.findByCategoryId(categoryId);

        transactionRepository.deleteAll(transactions);
        categoryRepository.delete(category);

        return category;
    }

    private Category getCategoryByIdAndUserId(Integer categoryId, Integer userId) {
        Category category = categoryRepository.findByIdAndUserId(categoryId, userId);

        if(category == null)
            throw new NoSuchElementException("No category found");

        return category;
    }
}
