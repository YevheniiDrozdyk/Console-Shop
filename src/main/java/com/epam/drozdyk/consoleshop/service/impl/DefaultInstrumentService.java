package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.repository.InstrumentRepository;
import com.epam.drozdyk.consoleshop.service.InstrumentService;

import java.util.HashMap;

/**
 * Default instrument service implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 3.0 19 Apr 2017
 */
public class DefaultInstrumentService implements InstrumentService {
    private final InstrumentRepository instrumentRepository;

    public DefaultInstrumentService(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @Override
    public Instrument getInstrument(String vendorCode) {
        return instrumentRepository.getInstrument(vendorCode);
    }

    @Override
    public HashMap<String, Instrument> getInstruments() {
        return instrumentRepository.getInstruments();
    }

    @Override
    public void putInstrument(String vendorCode, Instrument instrument) {
        instrumentRepository.putInstrument(vendorCode, instrument);
    }

    @Override
    public int getInstrumentCount() {
        return instrumentRepository.getInstrumentCount();
    }

    @Override
    public boolean isInstrumentExist(String vendorCode) {
        return instrumentRepository.getInstrument(vendorCode) != null;
    }
}
