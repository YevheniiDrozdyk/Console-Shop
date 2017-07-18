package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates inappropriate condition in serialization process.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 7 Apr 2017
 */
public class SerializationException extends ApplicationException {

    public SerializationException(String message) {
        super(message);
    }

    public SerializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
