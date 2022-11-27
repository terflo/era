package com.era.apiorder.controllers;

import com.era.apiorder.model.entities.Delivery;
import com.era.apiorder.model.services.interfaces.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping
    public Page<Delivery> getAll(Pageable pageable) {
        return deliveryService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Delivery getById(@PathVariable(name = "id") Long id) {
        return deliveryService.getById(id);
    }

    @PostMapping
    public Delivery save(Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @PutMapping
    public Delivery update(Delivery delivery) {
        return deliveryService.update(delivery);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(Delivery delivery) {
        deliveryService.delete(delivery);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        deliveryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
