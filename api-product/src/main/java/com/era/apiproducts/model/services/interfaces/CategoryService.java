package com.era.apiproducts.model.services.interfaces;

import com.era.apiproducts.model.entities.Category;
import com.era.apiproducts.model.exceptions.CategoryAlreadyExistException;
import com.era.apiproducts.model.exceptions.CategoryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category getCategoryById(Long id) throws CategoryNotFoundException;

    Category getCategoryByName(String name) throws CategoryNotFoundException;

    Page<Category> getAll(Pageable pageable);

    Category saveCategory(Category category) throws CategoryAlreadyExistException;

    Category updateCategory(Category category) throws CategoryAlreadyExistException, CategoryNotFoundException;

    void deleteCategory(Category category) throws CategoryNotFoundException;

    void deleteCategoryById(Long id) throws CategoryNotFoundException;

    boolean existsCategoryById(Long id);

    boolean existsCategoryByName(String name);

}
