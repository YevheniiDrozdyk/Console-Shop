package com.epam.drozdyk.consoleshop.repository.impl;

import com.epam.drozdyk.consoleshop.datasource.InstrumentSource;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.repository.InstrumentRepository;
import com.epam.drozdyk.consoleshop.util.io.InstrumentSerializer;

import java.util.HashMap;

/**
 * Default instrument repository implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 16 Apr 2017
 */
public class DefaultInstrumentRepository implements InstrumentRepository {
    private final InstrumentSource instrumentSource;

    public DefaultInstrumentRepository(InstrumentSerializer serializer) {
        instrumentSource = new InstrumentSource(serializer);
    }

    @Override
    public Instrument getInstrument(String vendorCode) {
        return instrumentSource.getInstrument(vendorCode);
    }

    @Override
    public HashMap<String, Instrument> getInstruments() {
        return instrumentSource.getInstruments();
    }

    @Override
    public void putInstrument(String vendorCode, Instrument instrument) {
        instrumentSource.putInstrument(vendorCode, instrument);
    }

    @Override
    public int getInstrumentCount() {
        return instrumentSource.getInstrumentsCount();
    }
}
