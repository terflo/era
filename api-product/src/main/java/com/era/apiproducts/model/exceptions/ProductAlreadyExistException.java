package com.era.apiproducts.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyExistException extends RuntimeException {

    public ProductAlreadyExistException(String name) {
        super(name);
    }

}
