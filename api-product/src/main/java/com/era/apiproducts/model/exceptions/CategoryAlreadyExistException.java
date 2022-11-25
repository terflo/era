package com.era.apiproducts.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryAlreadyExistException extends RuntimeException {

    public CategoryAlreadyExistException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
