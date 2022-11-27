package com.era.apiorder.model.repositories;

import com.era.apiorder.model.entities.OrdersProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProduct, Long> {
}
