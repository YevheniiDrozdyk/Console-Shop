package com.epam.drozdyk.consoleshop.repository;

import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.wrapper.LastFive;

/**
 * Defines methods for work with last five wrapper.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public interface LastFiveRepository {

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
