package com.shubhankar.debita.repository;

import com.shubhankar.debita.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Page<Category> findByUserId(Integer userId, Pageable pageable);
    Category findByIdAndUserId(Integer categoryId, Integer userId);
    Category findByTitleAndUserId(String title, Integer userId);
}
