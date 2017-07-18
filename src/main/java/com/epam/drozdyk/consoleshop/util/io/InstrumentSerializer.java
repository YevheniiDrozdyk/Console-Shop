package com.epam.drozdyk.consoleshop.util.io;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.SerializationException;
import com.epam.drozdyk.consoleshop.model.Instrument;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * An abstract realization of instruments map serializer.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public abstract class InstrumentSerializer {
    private String path;

    public InstrumentSerializer(String path) {
        this.path = path;
    }

    /**
     * Reads file with serialized instruments map.
     *
     * @return instruments map
     * @throws IOException
     */
    public HashMap<String, Instrument> readFile() throws IOException {
        HashMap<String, Instrument> instruments;
        try (ObjectInputStream inputStream = getInputStream()) {
            try {
                instruments = (HashMap<String, Instrument>) inputStream.readObject();
            } catch (ClassNotFoundException e) {
                throw new SerializationException(Message.ERROR_CLASS_NOT_FOUND, e);
            }
        }

        return instruments;
    }

    /**
     * Writes file with serialized instruments map.
     *
     * @param instruments instruments map
     * @param count       count of file writings
     * @throws IOException
     */
    public void writeFile(HashMap<String, Instrument> instruments, int count) throws IOException {
        try (ObjectOutputStream outputStream = getOutputStream()) {
            for (int i = 0; i < count; i++) {
                outputStream.writeObject(instruments);
            }
        }
    }

    protected String getPath() {
        return path;
    }

    /**
     * Returns kind of object input stream.
     * @return object input stream
     * @throws IOException
     */
    protected abstract ObjectInputStream getInputStream() throws IOException;

    /**
     * Returns kind of object output stream.
     * @return object output stream
     * @throws IOException
     */
    protected abstract ObjectOutputStream getOutputStream() throws IOException;
}
