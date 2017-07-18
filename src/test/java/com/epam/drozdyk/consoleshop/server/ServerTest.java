package com.epam.drozdyk.consoleshop.server;

import com.epam.drozdyk.consoleshop.constant.ServerType;
import com.epam.drozdyk.consoleshop.server.worker.Worker;
import com.epam.drozdyk.consoleshop.server.worker.factory.WorkerFactory;
import com.epam.drozdyk.consoleshop.server.worker.factory.WorkerFactoryContainer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServerTest {
    @InjectMocks
    private Server server;
    @Mock
    private ExecutorService threadPool;
    @Mock
    private WorkerFactoryContainer factoryContainer;
    @Mock
    private WorkerFactory workerFactory;
    @Mock
    private Worker worker;

    @Test
    public void run() throws Exception {
        when(factoryContainer.getWorkerFactory(any(ServerType.class))).thenReturn(workerFactory);
        when(workerFactory.createWorker(any())).thenReturn(worker);

        Thread thread = new Thread(server);
        thread.setDaemon(true);
        thread.start();

        Socket clientSocket = new Socket("localhost", ServerType.HTTP.getPort());

        Thread.sleep(10);
        verify(factoryContainer).getWorkerFactory(any());
        verify(workerFactory).createWorker(any(Socket.class));
    }
}