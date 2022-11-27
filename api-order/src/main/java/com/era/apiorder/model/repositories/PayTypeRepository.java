package com.era.apiorder.model.repositories;

import com.era.apiorder.model.entities.PayType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PayTypeRepository extends JpaRepository<PayType, Long> {

    Page<PayType> findAll(Pageable pageable);

    Optional<PayType> findById(Long id);

    Optional<PayType> findByName(String name);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void deleteByName(String name);

    boolean existsById(Long id);

    boolean existsByName(String name);

}
