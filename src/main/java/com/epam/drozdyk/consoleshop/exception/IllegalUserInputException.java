package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates that the user input is illegal.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 19 Apr 2017
 */
public class IllegalUserInputException extends ApplicationException {

    public IllegalUserInputException(String message) {
        super(message);
    }
}
