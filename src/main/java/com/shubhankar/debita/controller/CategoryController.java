package com.shubhankar.debita.controller;

import com.shubhankar.debita.model.Category;
import com.shubhankar.debita.request.CategoryRequest;
import com.shubhankar.debita.response.CategoryResponse;
import com.shubhankar.debita.service.CategoryService;
import com.shubhankar.debita.util.GetUserIdFromRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest,
            HttpServletRequest request) {
        Category createdCategory = categoryService.createCategory(categoryRequest, GetUserIdFromRequest.get(request));
        return new ResponseEntity<>(new CategoryResponse(createdCategory), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getUserCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            HttpServletRequest request) {
        Map<String, Object> response = categoryService.getUserCategories(GetUserIdFromRequest.get(request), page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Integer categoryId, HttpServletRequest request, @RequestBody CategoryRequest categoryRequest) {
        Category updatedCategory = categoryService.updateCategory(categoryId, GetUserIdFromRequest.get(request),categoryRequest);
        return new ResponseEntity<>(new CategoryResponse(updatedCategory), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable Integer categoryId, HttpServletRequest request) {
        Category deletedCategory = categoryService.deleteCategory(categoryId, GetUserIdFromRequest.get(request));
        return new ResponseEntity<>(new CategoryResponse(deletedCategory), HttpStatus.OK);
    }

}
