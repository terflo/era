package com.era.apiorder.model.services.interfaces;

import com.era.apiorder.model.entities.PayType;
import com.era.apiorder.model.exceptions.PayTypeAlreadyExistsException;
import com.era.apiorder.model.exceptions.PayTypeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PayTypeService {

        Page<PayType> getAll(Pageable pageable);

        PayType getById(Long id) throws PayTypeNotFoundException;

        PayType getByName(String name) throws PayTypeNotFoundException;

        PayType save(PayType payType) throws PayTypeAlreadyExistsException;

        PayType update(PayType payType) throws PayTypeAlreadyExistsException, PayTypeNotFoundException;

        void delete(Long id) throws PayTypeNotFoundException;

        void delete(PayType payType) throws PayTypeNotFoundException;

}
