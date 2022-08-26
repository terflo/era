package com.era.courseapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExamAlreadyExistsException extends RuntimeException {

    public ExamAlreadyExistsException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
