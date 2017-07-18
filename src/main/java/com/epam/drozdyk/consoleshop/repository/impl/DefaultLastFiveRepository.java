package com.epam.drozdyk.consoleshop.repository.impl;

import com.epam.drozdyk.consoleshop.repository.LastFiveRepository;
import com.epam.drozdyk.consoleshop.wrapper.LastFive;
import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Default last five repository implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class DefaultLastFiveRepository implements LastFiveRepository {
    private final LastFive lastFive;

    public DefaultLastFiveRepository() {
        lastFive = new LastFive();
    }

    @Override
    public LastFive getLastFive() {
        return lastFive;
    }

    @Override
    public void putInstrumentToLastFive(String vendorCode, Instrument instrument) {
        lastFive.put(vendorCode, instrument);
    }
}
