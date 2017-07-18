package com.epam.drozdyk.consoleshop.util.io.impl;

import com.epam.drozdyk.consoleshop.util.io.InstrumentSerializer;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Serializes instruments map in gzip-format.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class GZIPInstrumentSerializer extends InstrumentSerializer {

    public GZIPInstrumentSerializer(String path) {
        super(path);
    }

    @Override
    protected ObjectInputStream getInputStream() throws IOException {
        return new ObjectInputStream(new GZIPInputStream(new FileInputStream(getPath())));
    }

    @Override
    protected ObjectOutputStream getOutputStream() throws IOException {
        return new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(getPath())));
    }
}
