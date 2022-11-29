package com.era.apiproducts.controllers;

import com.era.apiproducts.model.entities.Product;
import com.era.apiproducts.model.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<Product> getAll(@PageableDefault(size = 20, direction = Sort.Direction.DESC, sort = "availableCount") Pageable pageable) {
        return productService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable(name = "id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
