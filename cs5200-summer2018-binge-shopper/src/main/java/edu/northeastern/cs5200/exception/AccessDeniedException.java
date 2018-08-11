package edu.northeastern.cs5200.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(Throwable throwable) {
        super(throwable);
    }
}
