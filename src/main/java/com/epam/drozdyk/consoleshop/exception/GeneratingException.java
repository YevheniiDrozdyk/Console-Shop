package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates inappropriate condition in generating process.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 7 Apr 2017
 */
public class GeneratingException extends ApplicationException {

    public GeneratingException(String message) {
        super(message);
    }

    public GeneratingException(String message, Throwable cause) {
        super(message, cause);
    }
}
