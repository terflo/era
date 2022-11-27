package com.era.apiorder.model.repositories;

import com.era.apiorder.model.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAll(Pageable pageable);

    Optional<Order> getDeliveryById(Long id);

    boolean existsById(Long id);

    @Transactional
    void deleteDeliveryById(Long id);


}
