package com.era.apiproducts.model.services.interfaces;

import com.era.apiproducts.model.entities.Material;
import com.era.apiproducts.model.exceptions.MaterialAlreadyExistException;
import com.era.apiproducts.model.exceptions.MaterialNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaterialService {

    Material getMaterialById(Long id) throws MaterialNotFoundException;

    Material getMaterialByName(String name) throws MaterialNotFoundException;

    Page<Material> getAll(Pageable pageable);

    Material saveMaterial(Material material) throws MaterialAlreadyExistException;

    Material updateMaterial(Material material) throws MaterialAlreadyExistException, MaterialNotFoundException;

    void deleteMaterial(Material material) throws MaterialNotFoundException;

    void deleteMaterialById(Long id) throws MaterialNotFoundException;

    boolean existsMaterialById(Long id);

    boolean existsMaterialByName(String name);

}
