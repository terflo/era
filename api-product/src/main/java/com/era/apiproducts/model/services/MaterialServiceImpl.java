package com.era.apiproducts.model.services;

import com.era.apiproducts.model.entities.Material;
import com.era.apiproducts.model.exceptions.MaterialAlreadyExistException;
import com.era.apiproducts.model.exceptions.MaterialNotFoundException;
import com.era.apiproducts.model.repositories.MaterialRepository;
import com.era.apiproducts.model.services.interfaces.MaterialService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    @Override
    public Material getMaterialById(Long id) throws MaterialNotFoundException {
        return materialRepository.getMaterialById(id).orElseThrow(() -> {
            throw new MaterialNotFoundException(String.format("Материал с id \"%s\" не найден", id));
        });
    }

    @Override
    public Material getMaterialByName(String name) throws MaterialNotFoundException {
        return materialRepository.getMaterialByName(name).orElseThrow(() -> {
            throw new MaterialNotFoundException(String.format("Материал с именем \"%s\" не найден", name));
        });
    }

    @Override
    public Page<Material> getAll(Pageable pageable) {
        return materialRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Material saveMaterial(@NotNull Material material) throws MaterialAlreadyExistException {

        if(materialRepository.existsMaterialByName(material.getName()))
            throw new MaterialAlreadyExistException(String.format("Материал с именем \"%s\" уже существует", material.getName()));

        if(materialRepository.existsMaterialById(material.getId()))
            throw new MaterialAlreadyExistException(String.format("Материал с id \"%s\" уже существует", material.getId()));

        return materialRepository.save(material);
    }

    @Override
    @Transactional
    public Material updateMaterial(@NotNull Material material) throws MaterialAlreadyExistException, MaterialNotFoundException, IllegalArgumentException {

        Material storedMaterial = materialRepository.getMaterialById(material.getId()).orElseThrow(() -> {
            throw new MaterialNotFoundException(String.format("Материал с id \"%s\" не найден", material.getId()));
        });
        storedMaterial.setName(material.getName());
        return materialRepository.save(storedMaterial);
    }

    @Override
    public void deleteMaterial(@NotNull Material material) throws MaterialNotFoundException, IllegalArgumentException {
        this.deleteMaterialById(material.getId());
    }

    @Override
    public void deleteMaterialById(Long id) throws MaterialNotFoundException, IllegalArgumentException {
        if(!materialRepository.existsMaterialById(id))
            throw new MaterialNotFoundException(String.format("Материал с id \"%s\" не найден", id));

        materialRepository.deleteMaterialById(id);
    }

    @Override
    public boolean existsMaterialById(Long id) {
        return materialRepository.existsMaterialById(id);
    }

    @Override
    public boolean existsMaterialByName(String name) {
        return materialRepository.existsMaterialByName(name);
    }
}
