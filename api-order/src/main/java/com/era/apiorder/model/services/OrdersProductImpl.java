package com.era.apiorder.model.services;

import com.era.apiorder.model.entities.OrdersProduct;
import com.era.apiorder.model.exceptions.OrdersProductNotFoundException;
import com.era.apiorder.model.repositories.OrdersProductRepository;
import com.era.apiorder.model.services.interfaces.OrdersProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersProductImpl implements OrdersProductService {

    private final OrdersProductRepository ordersProductRepository;

    @Override
    public Page<OrdersProduct> getAll(Pageable pageable) {
        return ordersProductRepository.findAll(pageable);
    }

    @Override
    public OrdersProduct getById(Long id) throws OrdersProductNotFoundException {
        return ordersProductRepository.findById(id).orElseThrow(() -> {
            throw new OrdersProductNotFoundException(String.format("Продукт заказа с id \"%s\" не найден", id));
        });
    }

    @Override
    @Transactional
    public OrdersProduct save(OrdersProduct ordersProduct) {
        if(ordersProduct.getId() != null)
            throw new IllegalArgumentException("Продукт заказа должен иметь null id");
        return ordersProductRepository.save(ordersProduct);
    }

    @Override
    @Transactional
    public OrdersProduct update(OrdersProduct ordersProduct) throws OrdersProductNotFoundException {
        if(ordersProductRepository.existsById(ordersProduct.getId()))
            throw new OrdersProductNotFoundException(String.format("Продукт заказа с id \"%s\" не найден", ordersProduct.getId()));
        return ordersProductRepository.save(ordersProduct);
    }

    @Override
    public void delete(Long id) throws OrdersProductNotFoundException {
        if(ordersProductRepository.existsById(id))
            throw new OrdersProductNotFoundException(String.format("Продукт заказа с id \"%s\" не найден", id));
        ordersProductRepository.deleteById(id);
    }

    @Override
    public void delete(OrdersProduct ordersProduct) throws OrdersProductNotFoundException {
        this.delete(ordersProduct.getId());
    }
}
