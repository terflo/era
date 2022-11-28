package com.era.apiorder.controllers;

import com.era.apiorder.model.entities.OrdersProduct;
import com.era.apiorder.model.services.interfaces.OrdersProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ordersProducts")
public class OrdersProductController {

    private final OrdersProductService ordersProductService;

    @GetMapping
    public Page<OrdersProduct> getAll(Pageable pageable) {
        return ordersProductService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public OrdersProduct getById(@PathVariable(name = "id") Long id) {
        return ordersProductService.getById(id);
    }

    @PostMapping
    public OrdersProduct save(@Valid OrdersProduct ordersProduct) {
        return ordersProductService.save(ordersProduct);
    }

    @PutMapping
    public OrdersProduct update(@Valid OrdersProduct ordersProduct) {
        return ordersProductService.update(ordersProduct);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid OrdersProduct ordersProduct) {
        ordersProductService.delete(ordersProduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        ordersProductService.delete(id);
        return ResponseEntity.ok().build();
    }

}
