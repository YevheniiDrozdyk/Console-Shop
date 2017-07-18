package com.epam.drozdyk.consoleshop.server;

import com.epam.drozdyk.consoleshop.constant.ServerType;
import com.epam.drozdyk.consoleshop.exception.ServerException;
import com.epam.drozdyk.consoleshop.server.worker.factory.WorkerFactoryContainer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server that can process tcp and http requests.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public class Server extends Thread {
    private static final int THREAD_POOL_SIZE = 16;
    private final ServerType serverType;
    private final int port;
    private ExecutorService threadPool;
    private ServerSocket serverSocket;
    private WorkerFactoryContainer factoryContainer;

    public Server(ServerType serverType, int port) {
        this.serverType = serverType;
        this.port = port;
        this.setDaemon(true);
        this.threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        this.factoryContainer = new WorkerFactoryContainer();
    }

    protected Server() {
        serverType = ServerType.HTTP;
        port = ServerType.HTTP.getPort();
    }

    @Override
    public void run() {
        openServerSocket();
        while (!isInterrupted()) {
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                threadPool.execute(factoryContainer.getWorkerFactory(serverType).createWorker(clientSocket));
            } catch (IOException e) {
                throw new ServerException(e.getMessage(), e.getCause());
            }
        }
    }

    private void openServerSocket() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new ServerException(e.getMessage(), e.getCause());
        }
    }
}
