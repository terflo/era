package com.era.authserver.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
