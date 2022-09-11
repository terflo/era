package com.era.apiuser.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}