package com.epam.drozdyk.consoleshop.builder.impl.template;

import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Builds fields of violin instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class DefaultGuitarBuilder extends DefaultStringedInstrumentBuilder {
    private GuitarType guitarType;

    public DefaultGuitarBuilder(InstrumentGenerator generator) {
        super(generator);
    }

    @Override
    public Instrument buildInstrument() {
        generateFields();

        return new Guitar(getId(), getVendorCode(), getProducer(), getPrice(), getStringCount(), guitarType);
    }

    @Override
    public void generateFields() {
        super.generateFields();
        this.guitarType = getGenerator().generateGuitarType(FieldName.GUITAR_TYPE_FIELD);
    }
}
