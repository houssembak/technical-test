package com.example.demo.services;

import com.example.demo.entities.Category;
import com.example.demo.entities.Subcategory;
import com.example.demo.interfaces.CategoryRepository;
import com.example.demo.interfaces.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SubcategoryService {
    private SubcategoryRepository subcategoryRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public SubcategoryService(SubcategoryRepository subcategoryRepository, CategoryRepository categoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public Subcategory saveSubcategory(Subcategory subcategory, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            subcategory.setImage(imageFile.getBytes());
        }
        return subcategoryRepository.save(subcategory);
    }

    public Subcategory addSubcategoryToCategory(Long categoryId, Subcategory subcategory) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            subcategory.setCategory(category);

            // Ajoutez la sous-catégorie à la liste des sous-catégories de la catégorie
            category.getSubcategories().add(subcategory);

            categoryRepository.save(category);
            return subcategoryRepository.save(subcategory);
        }
        return null;
    }
    public List<Subcategory> getAllSubcategories() {
        return subcategoryRepository.findAll();
    }

    public Optional<Subcategory> getSubcategoryById(Long id) {
        return subcategoryRepository.findById(id);
    }

    public Subcategory saveSubcategory(Subcategory subcategory) {
        return subcategoryRepository.save(subcategory);
    }

    public void deleteSubcategory(Long id) {
        subcategoryRepository.deleteById(id);
    }
    public List<Subcategory> getSubcategoriesByCategoryId(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return category.getSubcategories();
        } else {

            return Collections.emptyList();
        }
    }

}
