package org.example;

/**
 * Indicates that a method is not implemented by the client.
 * In general any method that throws this exception should
 * not be called on the client.
 */
public class NotImplementedByClientException extends Exception {
    public NotImplementedByClientException() {
    }

    public NotImplementedByClientException(String message) {
        super(message);
    }

    public NotImplementedByClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotImplementedByClientException(Throwable cause) {
        super(cause);
    }
}
