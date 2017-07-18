package com.epam.drozdyk.consoleshop.service;

import com.epam.drozdyk.consoleshop.model.Instrument;

import java.util.HashMap;

/**
 * Defines service methods to work with instrument repository.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 16 Apr 2017
 */
public interface InstrumentService {

    /**
     * Returns instrument object by its vendor code.
     *
     * @param vendorCode vendor code of necessary instrument
     * @return instrument object
     */
    Instrument getInstrument(String vendorCode);

    /**
     * Returns instruments map from data source.
     *
     * @return instruments map
     */
    HashMap<String, Instrument> getInstruments();

    /**
     * Adds new instrument to data source.
     *
     * @param vendorCode vendor code of new instrument
     * @param instrument new instrument object
     */
    void putInstrument(String vendorCode, Instrument instrument);

    /**
     * Returns instruments count from instrument source.
     *
     * @return instruments count
     */
    int getInstrumentCount();

    /**
     * Checks instrument existence in instrument source.
     *
     * @param vendorCode vendorCode of instrument
     * @return true if instrument exist
     */
    boolean isInstrumentExist(String vendorCode);
}
