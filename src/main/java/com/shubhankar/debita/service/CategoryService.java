package com.shubhankar.debita.service;

import com.shubhankar.debita.model.Category;
import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.CategoryRepository;
import com.shubhankar.debita.request.CategoryRequest;
import com.shubhankar.debita.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(UserService userService, CategoryRepository categoryRepository) {
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryRequest categoryRequest, Integer userId) {
        User user = userService.getUser(userId);
        Category category = new Category(user, categoryRequest.getTitle(), categoryRequest.getDescription(), (double) 0);
        category = categoryRepository.save(category);
        return category;
    }

    public Category getCategory(Integer categoryId) {
        return categoryRepository.findById(categoryId).get();
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
        result.put("userId", userId);

        return result;
    }

    public Category updateCategory(Integer id, CategoryRequest categoryRequest) {
        Category category = getCategory(id);
        category.setTitle(categoryRequest.getTitle());
        category.setDescription(categoryRequest.getDescription());

        categoryRepository.save(category);

        return category;
    }

    public Category deleteCategory(Integer categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.delete(category);
        return category;
    }
}
