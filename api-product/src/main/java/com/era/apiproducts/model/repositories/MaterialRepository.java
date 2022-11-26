package com.era.apiproducts.model.repositories;

import com.era.apiproducts.model.entities.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    Page<Material> findAll(Pageable pageable);

    Optional<Material> getMaterialById(Long id);

    Optional<Material> getMaterialByName(String name);

    boolean existsMaterialByName(String name);

    boolean existsMaterialById(Long id);

    @Transactional
    void deleteMaterialById(Long id);

    void deleteMaterialByName(String name);

}
