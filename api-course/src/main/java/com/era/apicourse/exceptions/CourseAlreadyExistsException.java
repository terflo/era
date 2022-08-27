package com.era.apicourse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CourseAlreadyExistsException extends RuntimeException{

    public CourseAlreadyExistsException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
