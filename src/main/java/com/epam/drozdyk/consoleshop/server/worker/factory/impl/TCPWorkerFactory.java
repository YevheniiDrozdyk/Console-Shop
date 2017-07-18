package com.epam.drozdyk.consoleshop.server.worker.factory.impl;

import com.epam.drozdyk.consoleshop.server.worker.factory.WorkerFactory;
import com.epam.drozdyk.consoleshop.server.worker.impl.TCPWorker;

import java.net.Socket;

/**
 * TCP factory implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public class TCPWorkerFactory implements WorkerFactory {

    @Override
    public Runnable createWorker(Socket clientSocket) {
        return new TCPWorker(clientSocket);
    }
}
