package com.epam.drozdyk.consoleshop.builder.impl.template;

import com.epam.drozdyk.consoleshop.constant.FieldName;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;

/**
 * Builds fields of guitar instrument.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class DefaultViolinBuilder extends DefaultStringedInstrumentBuilder {
    private ViolinCategory violinCategory;

    public DefaultViolinBuilder(InstrumentGenerator generator) {
        super(generator);
    }

    @Override
    public Instrument buildInstrument() {
        generateFields();

        return new Violin(getId(), getVendorCode(), getProducer(), getPrice(), getStringCount(), violinCategory);
    }

    @Override
    public void generateFields() {
        super.generateFields();
        this.violinCategory = getGenerator().generateViolinCategory(FieldName.VIOLIN_CATEGORY_FIELD);
    }
}
