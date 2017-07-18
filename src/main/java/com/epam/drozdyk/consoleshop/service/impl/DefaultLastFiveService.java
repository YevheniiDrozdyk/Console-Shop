package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.repository.LastFiveRepository;
import com.epam.drozdyk.consoleshop.service.LastFiveService;
import com.epam.drozdyk.consoleshop.wrapper.LastFive;
import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Default last five service implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class DefaultLastFiveService implements LastFiveService {
    private LastFiveRepository lastFiveRepository;

    public DefaultLastFiveService(LastFiveRepository lastFiveRepository) {
        this.lastFiveRepository = lastFiveRepository;
    }

    @Override
    public LastFive getLastFive() {
        return lastFiveRepository.getLastFive();
    }

    @Override
    public void putInstrumentToLastFive(String vendorCode, Instrument instrument) {
        lastFiveRepository.putInstrumentToLastFive(vendorCode, instrument);
    }
}
