package com.adamtrev.portal.data;

public class StaleDataException extends RuntimeException {
    public StaleDataException(final String message) {
        super(message);
    }
}
