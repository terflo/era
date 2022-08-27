package com.era.apicourse.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
