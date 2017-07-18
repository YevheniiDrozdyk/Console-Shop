package com.epam.drozdyk.task9.main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        String request;
        String response;
        try (Socket clientSocket = new Socket("localhost", 3000);
             BufferedReader inFromClient = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            request = inFromClient.readLine();
            if ("exit".equals(request)) {
                clientSocket.close();
                return;
            }
            outToServer.writeBytes(request + "\n");
            while (true) {
                response = inFromServer.readLine();
                if (!response.isEmpty()) {
                    System.out.println(response);
                    break;
                }
            }
        }
    }
}
