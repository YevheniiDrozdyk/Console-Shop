package com.epam.drozdyk.consoleshop.exception;

/**
 * Indicates troubles while server is working.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 19 Apr 2017
 */
public class ServerException extends ApplicationException {

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
