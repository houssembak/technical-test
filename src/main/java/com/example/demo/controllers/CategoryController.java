package com.example.demo.controllers;


import com.example.demo.entities.Category;
import com.example.demo.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(
            @RequestBody Category category,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            Category savedCategory = categoryService.saveCategory(category, imageFile);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Optional<Category> existingCategory = categoryService.getCategoryById(id);

        if (existingCategory.isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}