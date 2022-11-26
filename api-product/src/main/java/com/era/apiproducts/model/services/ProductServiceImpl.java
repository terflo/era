package com.era.apiproducts.model.services;

import com.era.apiproducts.model.entities.Product;
import com.era.apiproducts.model.exceptions.ProductAlreadyExistException;
import com.era.apiproducts.model.exceptions.ProductNotFoundException;
import com.era.apiproducts.model.repositories.ProductRepository;
import com.era.apiproducts.model.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        return productRepository.getProductById(id).orElseThrow(() -> {
            throw new ProductNotFoundException(String.format("Товар с id \"%s\" не найден", id));
        });
    }

    @Override
    public Product getProductByName(String name) throws ProductNotFoundException {
        return productRepository.getProductByName(name).orElseThrow(() -> {
            throw new ProductNotFoundException(String.format("Товар с именем \"%s\" не найден", name));
        });
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product saveProduct(@NotNull Product product) throws ProductAlreadyExistException {

        if(productRepository.existsProductByName(product.getName()))
            throw new ProductAlreadyExistException(String.format("Продукт с именем \"%s\" уже существует", product.getName()));

        if(productRepository.existsProductById(product.getId()))
            throw new ProductAlreadyExistException(String.format("Продукт с id \"%s\" уже существует", product.getId()));

        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(@NotNull Product product) throws ProductAlreadyExistException, ProductNotFoundException {
        if(productRepository.existsProductByName(product.getName()))
            throw new ProductAlreadyExistException(String.format("Продукт с именем \"%s\" уже существует", product.getName()));

        Product storedProduct = productRepository.getProductById(product.getId()).orElseThrow(() -> {
            throw new ProductNotFoundException(String.format("Продукт с id \"%s\" не существует", product.getId()));
        });

        storedProduct.setName(product.getName());
        storedProduct.setDescription(product.getDescription());
        storedProduct.setCategory(product.getCategory());
        storedProduct.setAvailableCount(product.getAvailableCount());
        storedProduct.setPrice(product.getPrice());
        storedProduct.setWidth(product.getWidth());
        storedProduct.setHeight(product.getHeight());
        storedProduct.setDepth(product.getDepth());
        storedProduct.setMaterials(product.getMaterials());

        return productRepository.save(storedProduct);
    }

    @Override
    public void deleteProduct(@NotNull Product product) throws ProductNotFoundException {
        this.deleteProductById(product.getId());
    }

    @Override
    public void deleteProductById(Long id) throws ProductNotFoundException {
        if(productRepository.existsProductById(id))
            throw new ProductNotFoundException(String.format("Продукт с id \"%s\" не найден", id));
        productRepository.deleteProductById(id);
    }

    @Override
    public boolean existsProductById(Long id) {
        return productRepository.existsProductById(id);
    }

    @Override
    public boolean existsProductByName(String name) {
        return productRepository.existsProductByName(name);
    }
}
