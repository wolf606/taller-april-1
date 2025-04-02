package com.ormexample.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserExceptions {

    public static class UserNotFoundException extends ApiException {
        public UserNotFoundException(Long id) {
            super("User with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public static class EmailAlreadyExistsException extends ApiException {
        public EmailAlreadyExistsException(String email) {
            super("Email " + email + " is already in use.", HttpStatus.CONFLICT);
        }
    }
}
