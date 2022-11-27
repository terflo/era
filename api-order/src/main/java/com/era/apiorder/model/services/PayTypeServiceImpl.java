package com.era.apiorder.model.services;

import com.era.apiorder.model.entities.PayType;
import com.era.apiorder.model.exceptions.PayTypeAlreadyExistsException;
import com.era.apiorder.model.exceptions.PayTypeNotFoundException;
import com.era.apiorder.model.repositories.PayTypeRepository;
import com.era.apiorder.model.services.interfaces.PayTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PayTypeServiceImpl implements PayTypeService {

    private final PayTypeRepository payTypeRepository;

    @Override
    public Page<PayType> getAll(Pageable pageable) {
        return payTypeRepository.findAll(pageable);
    }

    @Override
    public PayType getById(Long id) throws PayTypeNotFoundException {
        return payTypeRepository.findById(id).orElseThrow(() -> {
            throw new PayTypeNotFoundException(String.format("Тип оплаты с id \"%s\" не существует", id));
        });
    }

    @Override
    public PayType getByName(String name) throws PayTypeNotFoundException {
        return payTypeRepository.findByName(name).orElseThrow(() -> {
            throw new PayTypeNotFoundException(String.format("Тип оплаты с именем \"%s\" не существует", name));
        });
    }

    @Override
    @Transactional
    public PayType save(PayType payType) throws PayTypeAlreadyExistsException {
        if(payTypeRepository.existsByName(payType.getName()))
            throw new PayTypeAlreadyExistsException(String.format("Тип оплаты с именем \"%s\" уже существует", payType.getName()));
        return payTypeRepository.save(payType);
    }

    @Override
    @Transactional
    public PayType update(PayType payType) throws PayTypeAlreadyExistsException, PayTypeNotFoundException {

        PayType storedPayType = payTypeRepository.findById(payType.getId()).orElseThrow(() -> {
            throw new PayTypeNotFoundException(String.format("Тип оплаты с id \"%s\" не существует", payType.getId()));
        });

        storedPayType.setName(payType.getName());

        return payTypeRepository.save(storedPayType);
    }

    @Override
    public void delete(Long id) throws PayTypeNotFoundException {
        if(!payTypeRepository.existsById(id))
            throw new PayTypeNotFoundException(String.format("Тип оплаты с id \"%s\" не существует", id));
        payTypeRepository.deleteById(id);
    }

    @Override
    public void delete(PayType payType) throws PayTypeNotFoundException {
        this.delete(payType.getId());
    }
}
