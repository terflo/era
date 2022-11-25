package com.era.apiproducts.controllers;

import com.era.apiproducts.model.entities.Category;
import com.era.apiproducts.model.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Page<Category> getAllCategory(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable(name = "id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public Category saveCategory(Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping
    public Category updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }

}
