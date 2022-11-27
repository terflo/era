package com.era.apiorder.model.services.interfaces;

import com.era.apiorder.model.entities.OrdersProduct;
import com.era.apiorder.model.exceptions.OrdersProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrdersProductService {

    Page<OrdersProduct> getAll(Pageable pageable);

    OrdersProduct getById(Long id) throws OrdersProductNotFoundException;

    OrdersProduct save(OrdersProduct ordersProduct);

    OrdersProduct update(OrdersProduct ordersProduct) throws OrdersProductNotFoundException;

    void delete(Long id) throws OrdersProductNotFoundException;

    void delete(OrdersProduct ordersProduct) throws OrdersProductNotFoundException;

}
