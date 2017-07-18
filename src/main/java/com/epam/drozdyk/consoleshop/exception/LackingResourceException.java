package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates application lacking resource.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 7 Apr 2017
 */
public class LackingResourceException extends ApplicationException {

    public LackingResourceException(String message) {
        super(message);
    }
}
