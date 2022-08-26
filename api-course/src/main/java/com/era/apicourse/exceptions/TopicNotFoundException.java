package com.era.courseapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TopicNotFoundException extends RuntimeException {

    public TopicNotFoundException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
