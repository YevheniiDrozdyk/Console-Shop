package com.epam.drozdyk.consoleshop.util.io.impl;

import com.epam.drozdyk.consoleshop.util.io.InstrumentSerializer;

import java.io.*;

/**
 * Serializes instruments map in file.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class FileInstrumentSerializer extends InstrumentSerializer {

    public FileInstrumentSerializer(String path) {
        super(path);
    }

    @Override
    protected ObjectInputStream getInputStream() throws IOException {
        return new ObjectInputStream(new FileInputStream(getPath()));
    }

    @Override
    protected ObjectOutputStream getOutputStream() throws IOException {
        return new ObjectOutputStream(new FileOutputStream(getPath()));
    }
}
