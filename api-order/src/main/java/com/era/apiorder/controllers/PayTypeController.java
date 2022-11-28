package com.era.apiorder.controllers;

import com.era.apiorder.model.entities.PayType;
import com.era.apiorder.model.services.interfaces.PayTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payTypes")
public class PayTypeController {

    private final PayTypeService payTypeService;

    @GetMapping
    public Page<PayType> getAll(Pageable pageable) {
        return payTypeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public PayType getById(@PathVariable(name = "id") Long id) {
        return payTypeService.getById(id);
    }

    @PostMapping
    public PayType save(@Valid PayType payType) {
        return payTypeService.save(payType);
    }

    @PutMapping
    public PayType update(@Valid PayType payType) {
        return payTypeService.update(payType);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid PayType payType) {
        payTypeService.delete(payType);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        payTypeService.delete(id);
        return ResponseEntity.ok().build();
    }

}
