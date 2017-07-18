package com.epam.drozdyk.consoleshop.builder;

import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Defines method for building of instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 6 Apr 2017
 */
public interface InstrumentBuilder {

    /**
     * Builds instrument object.
     *
     * @return instrument object.
     */
    Instrument buildInstrument();
}
