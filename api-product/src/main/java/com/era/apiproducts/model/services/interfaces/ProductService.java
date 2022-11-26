package com.era.apiproducts.model.services.interfaces;

import com.era.apiproducts.model.entities.Product;
import com.era.apiproducts.model.exceptions.ProductAlreadyExistException;
import com.era.apiproducts.model.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product getProductById(Long id) throws ProductNotFoundException;

    Product getProductByName(String name) throws ProductNotFoundException;

    Page<Product> getAll(Pageable pageable);

    Product saveProduct(Product product) throws ProductAlreadyExistException;

    Product updateProduct(Product product) throws ProductAlreadyExistException, ProductNotFoundException;

    void deleteProduct(Product product) throws ProductNotFoundException;

    void deleteProductById(Long id) throws ProductNotFoundException;

    boolean existsProductById(Long id);

    boolean existsProductByName(String name);

}
