package com.era.apiorder.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DeliveryAlreadyExistsException extends RuntimeException {

    public DeliveryAlreadyExistsException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
