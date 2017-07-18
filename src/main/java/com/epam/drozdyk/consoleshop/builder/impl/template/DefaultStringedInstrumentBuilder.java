package com.epam.drozdyk.consoleshop.builder.impl.template;

import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Builds field of stringed instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public abstract class DefaultStringedInstrumentBuilder extends DefaultInstrumentBuilder {
    private int stringCount;

    public DefaultStringedInstrumentBuilder(InstrumentGenerator generator) {
        super(generator);
    }

    public abstract Instrument buildInstrument();

    @Override
    public void generateFields() {
        super.generateFields();
        this.stringCount = getGenerator().generateInt(FieldName.STRING_COUNT_FIELD);
    }

    protected int getStringCount() {
        return stringCount;
    }
}
