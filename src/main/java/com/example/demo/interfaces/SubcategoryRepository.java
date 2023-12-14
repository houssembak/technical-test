package com.example.demo.interfaces;

import com.example.demo.entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcategoryRepository extends JpaRepository<Subcategory,Long> {
}
