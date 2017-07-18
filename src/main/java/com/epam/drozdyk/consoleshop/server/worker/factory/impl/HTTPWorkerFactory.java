package com.epam.drozdyk.consoleshop.server.worker.factory.impl;

import com.epam.drozdyk.consoleshop.server.worker.factory.WorkerFactory;
import com.epam.drozdyk.consoleshop.server.worker.impl.HTTPWorker;

import java.net.Socket;

/**
 * HTTP factory implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public class HTTPWorkerFactory implements WorkerFactory {

    @Override
    public Runnable createWorker(Socket clientSocket) {
        return new HTTPWorker(clientSocket);
    }
}
