package com.era.apiorder.model.services;

import com.era.apiorder.model.entities.Delivery;
import com.era.apiorder.model.exceptions.DeliveryAlreadyExistsException;
import com.era.apiorder.model.exceptions.DeliveryNotFoundException;
import com.era.apiorder.model.repositories.DeliveryRepository;
import com.era.apiorder.model.services.interfaces.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public Page<Delivery> getAll(Pageable pageable) {
        return deliveryRepository.findAll(pageable);
    }

    @Override
    public Delivery getById(Long id) throws DeliveryNotFoundException {
        return deliveryRepository.getDeliveryById(id).orElseThrow(() -> {
            throw new DeliveryNotFoundException(String.format("Доставка с id \"%s\" не найдена", id));
        });
    }

    @Override
    public Delivery getByName(String name) throws DeliveryNotFoundException {
        return deliveryRepository.getDeliveryByName(name).orElseThrow(() -> {
            throw new DeliveryNotFoundException(String.format("Доставка с именем \"%s\" не найдена", name));
        });
    }

    @Override
    @Transactional
    public Delivery save(Delivery delivery) throws DeliveryAlreadyExistsException {
        if(deliveryRepository.existsByName(delivery.getName()))
            throw new DeliveryAlreadyExistsException(String.format("Доставка с именем \"%s\" уже существует", delivery.getName()));
        return deliveryRepository.save(delivery);
    }

    @Override
    @Transactional
    public Delivery update(Delivery delivery) throws DeliveryAlreadyExistsException, DeliveryNotFoundException {

        Delivery storedDelivery = deliveryRepository.getDeliveryById(delivery.getId()).orElseThrow(() -> {
            throw new DeliveryNotFoundException(String.format("Доставки с id \"%s\" не существует", delivery.getId()));
        });

        storedDelivery.setName(delivery.getName());

        return deliveryRepository.save(storedDelivery);
    }

    @Override
    public void delete(Delivery delivery) throws DeliveryNotFoundException {
        this.delete(delivery.getId());
    }

    @Override
    public void delete(Long id) throws DeliveryNotFoundException {
        if(!deliveryRepository.existsById(id))
            throw new DeliveryNotFoundException(String.format("Доставки с id \"%s\" не существует", id));
        deliveryRepository.deleteDeliveryById(id);
    }
}
