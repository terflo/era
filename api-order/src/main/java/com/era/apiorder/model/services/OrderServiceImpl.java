package com.era.apiorder.model.services;

import com.era.apiorder.model.entities.Order;
import com.era.apiorder.model.exceptions.OrderNotFoundException;
import com.era.apiorder.model.repositories.OrderRepository;
import com.era.apiorder.model.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order getById(Long id) throws OrderNotFoundException {
        return orderRepository.getDeliveryById(id).orElseThrow(() -> {
            throw new OrderNotFoundException(String.format("Заказ с id \"%s\" не найден", id));
        });
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order update(Order order) {
        if(!orderRepository.existsById(order.getId()))
            throw new OrderNotFoundException(String.format("Заказ с id \"%s\" не найден", order.getId()));
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) throws OrderNotFoundException {
        this.delete(order.getId());
    }

    @Override
    public void delete(Long id) throws OrderNotFoundException {
        if(!orderRepository.existsById(id))
            throw new OrderNotFoundException(String.format("Заказ с id \"%s\" не найден", id));
        orderRepository.deleteDeliveryById(id);
    }
}
