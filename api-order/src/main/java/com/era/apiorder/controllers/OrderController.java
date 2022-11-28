package com.era.apiorder.controllers;

import com.era.apiorder.model.entities.Order;
import com.era.apiorder.model.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Page<Order> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable(name = "id") Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public Order save(@Valid Order order) {
        return orderService.save(order);
    }

    @PutMapping
    public Order update(@Valid Order order) {
        return orderService.update(order);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid Order order) {
        orderService.delete(order);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
