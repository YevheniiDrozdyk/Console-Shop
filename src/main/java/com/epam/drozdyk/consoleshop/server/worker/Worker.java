package com.epam.drozdyk.consoleshop.server.worker;

import com.epam.drozdyk.consoleshop.command.CommandController;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.ServerException;

import java.io.*;
import java.net.Socket;

/**
 * An abstract server worker that always opens new socket
 * for new client.
 * Reads request from client and than writes response.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public abstract class Worker implements Runnable {
    protected final CommandController serverController;
    private final Socket clientSocket;

    public Worker(Socket clientSocket) {
        this.serverController = Context.getInstance().getServerController();
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter output = new BufferedWriter(
                     new PrintWriter(clientSocket.getOutputStream()))) {
            String request = getRequest(input);
            writeResponse(output, request);
        } catch (IOException e) {
            throw new ServerException(e.getMessage(), e.getCause());
        } finally {
            closeClientSocket();
        }
    }

    /**
     * Reads user request and than validate it and builds server command.
     *
     * @param input user input stream
     * @return server command string object
     * @throws IOException throws when some stream issues happens
     */
    protected abstract String getRequest(BufferedReader input) throws IOException;

    /**
     * Writes response to user.
     *
     * @param output  user output stream
     * @param request server command
     * @throws IOException throws when some stream issues happens
     */
    protected abstract void writeResponse(BufferedWriter output, String request) throws IOException;

    private void closeClientSocket() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            throw new ServerException(e.getMessage(), e.getCause());
        }
    }
}
