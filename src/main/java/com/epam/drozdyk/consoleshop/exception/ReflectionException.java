package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates inappropriate condition in reflection process.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 7 Apr 2017
 */
public class ReflectionException extends ApplicationException {

    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
