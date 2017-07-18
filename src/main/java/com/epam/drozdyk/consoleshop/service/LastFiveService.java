package com.epam.drozdyk.consoleshop.service;

import com.epam.drozdyk.consoleshop.wrapper.LastFive;
import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Defines service methods to work with last five repository.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public interface LastFiveService {

    /**
     * Returns last five wrapper object.
     *
     * @return last five wrapper object.
     */
    LastFive getLastFive();

    /**
     * Puts new instrument to last five wrapper.
     *
     * @param vendorCode vendor code of new instrument
     * @param instrument new instrument object
     */
    void putInstrumentToLastFive(String vendorCode, Instrument instrument);
}
