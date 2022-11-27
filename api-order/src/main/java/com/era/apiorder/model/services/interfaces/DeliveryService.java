package com.era.apiorder.model.services.interfaces;

import com.era.apiorder.model.entities.Delivery;
import com.era.apiorder.model.exceptions.DeliveryAlreadyExistsException;
import com.era.apiorder.model.exceptions.DeliveryNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {

    Page<Delivery> getAll(Pageable pageable);

    Delivery getById(Long id) throws DeliveryNotFoundException;

    Delivery getByName(String name) throws DeliveryNotFoundException;

    Delivery save(Delivery delivery) throws DeliveryAlreadyExistsException;

    Delivery update(Delivery delivery) throws DeliveryAlreadyExistsException, DeliveryNotFoundException;

    void delete(Delivery delivery) throws DeliveryNotFoundException;

    void delete(Long id) throws DeliveryNotFoundException;

}
