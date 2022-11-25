package com.era.apiproducts.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
