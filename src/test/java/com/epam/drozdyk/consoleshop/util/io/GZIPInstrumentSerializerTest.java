package com.epam.drozdyk.consoleshop.util.io;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.util.io.impl.GZIPInstrumentSerializer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class GZIPInstrumentSerializerTest {
    private HashMap<String, Instrument> instruments;
    private InstrumentSerializer serializer;

    @Before
    public void setUp() {
        instruments = new LinkedHashMap<>();
        instruments.put("F300", new Guitar(1, "F300", "Cort", 3000, 6, GuitarType.ACOUSTIC));
        instruments.put("F400", new Guitar(2, "F400", "Yamaha", 2000, 6, GuitarType.ACOUSTIC));

        serializer = new GZIPInstrumentSerializer("gzip.dat");
    }

    @Test
    public void testWriteToGzip() throws IOException {
        serializer.writeFile(instruments, 1);
    }
}
