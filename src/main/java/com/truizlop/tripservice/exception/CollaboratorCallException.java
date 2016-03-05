package com.truizlop.tripservice.exception;

public class CollaboratorCallException extends RuntimeException {
    public CollaboratorCallException() {
        super();
    }

    public CollaboratorCallException(String message,
                                     Throwable cause) {
        super(message, cause);
    }

    public CollaboratorCallException(String message) {
        super(message);
    }

    public CollaboratorCallException(Throwable cause) {
        super(cause);
    }
}
