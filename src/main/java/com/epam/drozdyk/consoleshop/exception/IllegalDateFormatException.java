package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates that the date format is illegal.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 19 Apr 2017
 */
public class IllegalDateFormatException extends ApplicationException {

    public IllegalDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
