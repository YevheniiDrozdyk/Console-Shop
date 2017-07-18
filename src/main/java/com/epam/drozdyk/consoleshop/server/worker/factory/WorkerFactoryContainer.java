package com.epam.drozdyk.consoleshop.server.worker.factory;

import com.epam.drozdyk.consoleshop.constant.ServerType;
import com.epam.drozdyk.consoleshop.server.worker.factory.impl.HTTPWorkerFactory;
import com.epam.drozdyk.consoleshop.server.worker.factory.impl.TCPWorkerFactory;

import java.util.HashMap;

/**
 * Contains worker factories for getting concrete thread worker.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 24 Apr 2017
 */
public class WorkerFactoryContainer {
    private HashMap<ServerType, WorkerFactory> factories;

    public WorkerFactoryContainer() {
        factories = new HashMap<>();
        factories.put(ServerType.TCP, new TCPWorkerFactory());
        factories.put(ServerType.HTTP, new HTTPWorkerFactory());
    }

    public WorkerFactory getWorkerFactory(ServerType serverType) {
        return factories.get(serverType);
    }
}
