package com.era.apiorder.model.repositories;

import com.era.apiorder.model.entities.OrdersProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProduct, Long> {

    Page<OrdersProduct> findAll(Pageable pageable);

    Optional<OrdersProduct> findById(Long id);

    @Transactional
    void deleteById(Long id);

    boolean existsById(Long id);

}
