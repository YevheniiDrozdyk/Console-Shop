package com.epam.drozdyk.consoleshop.exception;

/**
 * Demo application exception.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 7 Apr 2017
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
