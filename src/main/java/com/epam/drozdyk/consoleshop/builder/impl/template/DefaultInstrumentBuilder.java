package com.epam.drozdyk.consoleshop.builder.impl.template;

import com.epam.drozdyk.consoleshop.builder.InstrumentBuilder;
import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.model.Instrument;

/**
 * Builds base fields of each instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public abstract class DefaultInstrumentBuilder implements InstrumentBuilder {
    private InstrumentGenerator generator;

    private long id;
    private String vendorCode;
    private String producer;
    private int price;

    DefaultInstrumentBuilder(InstrumentGenerator generator) {
        this.generator = generator;
    }

    public abstract Instrument buildInstrument();

    public void generateFields() {
        this.id = generator.generateInt(FieldName.ID_FIELD);
        this.vendorCode = generator.generateString(FieldName.VENDOR_CODE_FIELD);
        this.producer = generator.generateString(FieldName.PRODUCER_FIELD);
        this.price = generator.generateInt(FieldName.PRICE_FIELD);
    }

    protected InstrumentGenerator getGenerator() {
        return generator;
    }

    protected long getId() {
        return id;
    }

    protected String getVendorCode() {
        return vendorCode;
    }

    protected String getProducer() {
        return producer;
    }

    protected int getPrice() {
        return price;
    }
}
