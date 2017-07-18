package com.epam.drozdyk.consoleshop.server.worker.factory;

import java.net.Socket;

/**
 * An abstract factory that defines factory type of server workers.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public interface WorkerFactory {

    Runnable createWorker(Socket clientSocket);
}
