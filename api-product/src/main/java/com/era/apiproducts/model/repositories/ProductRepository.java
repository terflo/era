package com.era.apiproducts.model.repositories;

import com.era.apiproducts.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> getProductById(Long id);

    Optional<Product> getProductByName(String name);

    boolean existsProductByName(String name);

    boolean existsProductById(Long id);

    @Transactional
    void deleteProductById(Long id);

    void deleteProductByName(String name);

}
