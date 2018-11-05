package org.sample.exception;

public class OnesProfileException extends RuntimeException {

    public OnesProfileException() {
        super();
    }

    public OnesProfileException(String message) {
        super(message);
    }

    public OnesProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public OnesProfileException(Throwable cause) {
        super(cause);
    }

    protected OnesProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
