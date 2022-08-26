package com.era.courseapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TopicAlreadyExistsException extends RuntimeException {

    public TopicAlreadyExistsException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
