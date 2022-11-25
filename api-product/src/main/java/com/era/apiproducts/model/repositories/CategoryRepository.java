package com.era.apiproducts.model.repositories;

import com.era.apiproducts.model.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> getCategoryById(Long id);

    Optional<Category> getCategoryByName(String name);

    boolean existsCategoryByName(String name);

    boolean existsCategoryById(Long id);

    @Transactional
    void deleteCategoryById(Long id);

    void deleteCategoryByName(String name);

}
