package com.epam.drozdyk.consoleshop.server.worker.impl;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.server.worker.Worker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * TCP worker implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public class TCPWorker extends Worker {

    public TCPWorker(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    protected String getRequest(BufferedReader input) throws IOException {
        String[] args = input.readLine().replaceAll("=", " ").split(" ");

        return buildCommand(args);
    }

    @Override
    protected void writeResponse(BufferedWriter output, String request) throws IOException {
        String response = serverController.execute(request).getMessage();
        output.write(response);
        output.flush();
    }

    private String buildCommand(String... args) {
        StringBuilder command = new StringBuilder();
        if (args.length < 2) {
            return Message.ERROR_MESSAGE_NO_SUCH_COMMANDS;
        }
        for (int i = 0; i < args.length; i++) {
            command.append(args[i]);
            if (i == 0) {
                command.append("-");
            } else {
                command.append(" ");
            }
        }

        return command.toString().trim();
    }
}
