package com.es.practiarmar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s con %s '%s' ya existe.", resourceName, fieldName, fieldValue));
    }
}
