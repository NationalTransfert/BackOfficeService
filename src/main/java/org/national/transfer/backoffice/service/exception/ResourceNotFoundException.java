package org.national.transfer.backoffice.service.exception;

import lombok.Getter;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private Integer statusCode;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
