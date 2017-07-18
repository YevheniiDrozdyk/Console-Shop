package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.builder.BuilderContainer;
import com.epam.drozdyk.consoleshop.builder.InstrumentBuilder;
import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.InstrumentService;

/**
 * An abstract command for adding a new item to
 * instrument source.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
abstract class AddNewItemCommand implements Command {
    static final int BUILDER_INDEX = 0;

    protected InstrumentService instrumentService;
    private BuilderContainer builderContainer;

    AddNewItemCommand() {
        this.instrumentService = Context.getInstance().getInstrumentService();
        this.builderContainer = Context.getInstance().getBuilderContainer();
    }

    void addNewInstrument(String builderKey) {
        InstrumentBuilder builder = builderContainer.getBuilder(builderKey);
        Instrument instrument = builder.buildInstrument();
        String vendorCode = instrument.getVendorCode();
        instrumentService.putInstrument(vendorCode, instrument);
    }
}
