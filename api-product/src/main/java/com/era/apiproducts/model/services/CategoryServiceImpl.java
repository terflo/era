package com.era.apiproducts.model.services;

import com.era.apiproducts.model.exceptions.CategoryAlreadyExistException;
import com.era.apiproducts.model.exceptions.CategoryNotFoundException;
import com.era.apiproducts.model.entities.Category;
import com.era.apiproducts.model.repositories.CategoryRepository;
import com.era.apiproducts.model.services.interfaces.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) throws CategoryNotFoundException {
        return categoryRepository
                .getCategoryById(id)
                .orElseThrow(() -> {
                    throw new CategoryNotFoundException(String.format("Категория с id \"%s\" не существует", id));
                });
    }

    @Override
    public Category getCategoryByName(String name) throws CategoryNotFoundException {
        return categoryRepository
                .getCategoryByName(name)
                .orElseThrow(() -> {
                    throw new CategoryNotFoundException(String.format("Категория с именем \"%s\" не существует", name));
                });
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Category saveCategory(Category category) throws CategoryAlreadyExistException {
        if(categoryRepository.existsCategoryByName(category.getName()))
            throw new CategoryAlreadyExistException(String.format("Категория с именем \"%s\" уже существует", category.getName()));
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category updateCategory(Category category) throws CategoryAlreadyExistException, CategoryNotFoundException {

        Category storedCategory = categoryRepository
                .getCategoryById(category.getId())
                .orElseThrow(() -> {
                    throw new CategoryNotFoundException(String.format("Категория с id \"%s\" не существует", category.getId()));
                });

        storedCategory.setName(category.getName());
        return categoryRepository.save(storedCategory);

    }

    @Override
    public void deleteCategory(Category category) throws CategoryNotFoundException {
        this.deleteCategoryById(category.getId());
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryNotFoundException {
        if(!categoryRepository.existsCategoryById(id))
            throw new CategoryNotFoundException(String.format("Категория с id \"%s\" не существует", id));
        categoryRepository.deleteCategoryById(id);
    }

    @Override
    public boolean existsCategoryById(Long id) {
        return categoryRepository.existsCategoryById(id);
    }

    @Override
    public boolean existsCategoryByName(String name) {
        return categoryRepository.existsCategoryByName(name);
    }
}
