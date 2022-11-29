package com.era.apiproducts.controllers;

import com.era.apiproducts.model.entities.Material;
import com.era.apiproducts.model.services.interfaces.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping
    public Page<Material> getAll(@PageableDefault(size = 20, direction = Sort.Direction.DESC, sort = "name") Pageable pageable) {
        return materialService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Material getById(@PathVariable(name = "id") Long id) {
        return materialService.getMaterialById(id);
    }

    @PostMapping
    public Material saveMaterial(@RequestBody Material material) {
        return materialService.saveMaterial(material);
    }

    @PutMapping
    public Material updateMaterial(@RequestBody Material material) {
        return materialService.updateMaterial(material);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMaterial(@PathVariable(name = "id") Long id) {
        materialService.deleteMaterialById(id);
        return ResponseEntity.ok().build();
    }

}
