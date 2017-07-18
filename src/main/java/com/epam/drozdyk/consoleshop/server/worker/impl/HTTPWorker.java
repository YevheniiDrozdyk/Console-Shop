package com.epam.drozdyk.consoleshop.server.worker.impl;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.server.util.HTTPHeader;
import com.epam.drozdyk.consoleshop.server.util.RequestParser;
import com.epam.drozdyk.consoleshop.server.worker.Worker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * HTTP worker implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public class HTTPWorker extends Worker {

    public HTTPWorker(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    protected String getRequest(BufferedReader input) throws IOException {
        try {
            String url = RequestParser.getUrl(input.readLine());
            return buildCommand(url.split("/"));
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            return Message.ERROR_ILLEGAL_ARGS;
        }
    }

    @Override
    protected void writeResponse(BufferedWriter output, String request) throws IOException {
        String result = serverController.execute(request).getMessage();
        String json = buildJson(result);
        String response = HTTPHeader.buildHttpResponse(json);
        output.write(response);
        output.flush();
    }

    private String buildCommand(String... args) {
        StringBuilder command = new StringBuilder();
        if (args.length == 2) {
            return Message.ERROR_MESSAGE_NO_SUCH_COMMANDS;
        }
        if ("shop".equals(args[1])) {
            command.append("get-");
        }
        if (args[2].contains("?")) {
            String[] params = args[2].split("\\?");
            command.append(params[0]);
            if (params[1].contains("=")) {
                String[] values = params[1].split("=");
                if ("get_info".equals(values[0])) {
                    command.append(" ");
                    command.append(values[1]);
                }
            }
        } else {
            command.append(args[2]);
        }

        return command.toString();
    }

    private String buildJson(String result) {
        StringBuilder json = new StringBuilder("{\r\n");
        json.append(result);
        json.append("\r\n");
        json.append("}\r\n");

        return json.toString();
    }
}
