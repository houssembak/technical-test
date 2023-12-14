package com.example.demo.controllers;

import com.example.demo.entities.Subcategory;
import com.example.demo.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subcategories")
public class SubcategoryController {
    private SubcategoryService subcategoryService;
    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }
    @PostMapping("/addSubcategoryToCategory/{categoryId}")
    public Subcategory addSubcategoryToCategory(
            @PathVariable Long categoryId,
            @RequestBody Subcategory subcategory) {
        return subcategoryService.addSubcategoryToCategory(categoryId, subcategory);
    }

    @GetMapping
    public List<Subcategory> getAllSubcategories() {
        return subcategoryService.getAllSubcategories();
    }

    @GetMapping("/{id}")
    public Optional<Subcategory> getSubcategoryById(@PathVariable Long id) {

        return subcategoryService.getSubcategoryById(id);
    }
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<Subcategory>> getSubcategoriesByCategoryId(@PathVariable Long categoryId) {
        List<Subcategory> subcategories = subcategoryService.getSubcategoriesByCategoryId(categoryId);

        if (!subcategories.isEmpty()) {
            return ResponseEntity.ok(subcategories);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Subcategory createSubcategory(@RequestBody Subcategory subcategory) {
        return subcategoryService.saveSubcategory(subcategory);
    }

    @PutMapping("/{id}")
    public Subcategory updateSubcategory(@PathVariable Long id, @RequestBody Subcategory subcategory) {
        // Ajoutez la logique pour mettre à jour la sous-catégorie
        return subcategoryService.saveSubcategory(subcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        Optional<Subcategory> existingSubcategory = subcategoryService.getSubcategoryById(id);

        if (existingSubcategory.isPresent()) {
            subcategoryService.deleteSubcategory(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
