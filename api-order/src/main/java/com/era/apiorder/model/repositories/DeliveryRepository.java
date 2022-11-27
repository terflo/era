package com.era.apiorder.model.repositories;

import com.era.apiorder.model.entities.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Page<Delivery> findAll(Pageable pageable);

    Optional<Delivery> getDeliveryById(Long id);

    Optional<Delivery> getDeliveryByName(String name);

    boolean existsByName(String name);

    boolean existsById(Long id);

    @Transactional
    void deleteDeliveryById(Long id);

    @Transactional
    void deleteDeliveryByName(String name);

}
