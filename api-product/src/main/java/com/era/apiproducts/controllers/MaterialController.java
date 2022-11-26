package com.era.apiproducts.controllers;

import com.era.apiproducts.model.entities.Material;
import com.era.apiproducts.model.services.interfaces.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    public Page<Material> getAll(Pageable pageable) {
        return materialService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Material getById(@PathVariable(name = "id") Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    public Material saveMaterial(Material material) {
        return materialService.saveMaterial(material);
    }

    @PutMapping
    public Material updateMaterial(Material material) {
        return materialService.updateMaterial(material);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable(name = "id") Long id) {
        materialService.deleteMaterialById(id);
        return ResponseEntity.ok().build();
    }

}
