package com.epam.drozdyk.consoleshop.builder;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.NoSuchInstrumentException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Can be contained instrument builders.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class BuilderContainer {
    private Map<String, InstrumentBuilder> builders;

    public BuilderContainer() {
        builders = new LinkedHashMap<>();
    }

    public void addBuilder(String key, InstrumentBuilder builder) {
        builders.put(key, builder);
    }

    public InstrumentBuilder getBuilder(String key) {
        InstrumentBuilder builder = builders.get(key);
        if (builder != null) {
            return builder;
        }

        throw new NoSuchInstrumentException(Message.ERROR_NO_SUCH_INSTRUMENT);
    }
}
