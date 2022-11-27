package com.era.apiorder.model.services.interfaces;

import com.era.apiorder.model.entities.Order;
import com.era.apiorder.model.exceptions.OrderNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> getAll(Pageable pageable);

    Order getById(Long id) throws OrderNotFoundException;

    Order save(Order order);

    Order update(Order order);

    void delete(Order order) throws OrderNotFoundException;

    void delete(Long id) throws OrderNotFoundException;

}
