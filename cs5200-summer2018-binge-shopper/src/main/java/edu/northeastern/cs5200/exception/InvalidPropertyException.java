package edu.northeastern.cs5200.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidPropertyException extends RuntimeException{

    public InvalidPropertyException(String message) {
        super(message);
    }
}
