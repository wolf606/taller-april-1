package com.ormexample.demo.exception;

import org.springframework.http.HttpStatus;

public class PersonaExceptions {

    public static class PersonaNotFoundException extends ApiException {
        public PersonaNotFoundException(Long id) {
            super("Persona with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
