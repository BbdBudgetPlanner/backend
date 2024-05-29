package com.bbd.BudgetPlanner.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbd.BudgetPlanner.models.Category;
import com.bbd.BudgetPlanner.repository.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class CategoryController {
    
    private final CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/categories")
    public ResponseEntity<?> allCategories() {
        List<Category> categories = repo.findAll();
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(categories);
    }

    @PostMapping("/api/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        Category entity = repo.save(category);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(entity);
    }
}
