package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates that the element being requested is not exist.
 * does not exist
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 18 Apr 2017
 */
public class NoSuchInstrumentException extends ApplicationException {

    public NoSuchInstrumentException(String message) {
        super(message);
    }
}
