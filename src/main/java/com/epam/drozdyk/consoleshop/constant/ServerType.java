package com.epam.drozdyk.consoleshop.constant;

/**
 * Holds server types and their ports.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public enum ServerType {
    TCP(3000), HTTP(80);

    private int port;

    ServerType(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}
