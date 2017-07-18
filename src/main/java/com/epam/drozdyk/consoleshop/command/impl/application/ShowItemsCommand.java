package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.AvailableInstrumentsView;
import com.epam.drozdyk.consoleshop.view.ErrorView;
import com.epam.drozdyk.consoleshop.view.View;

import java.util.HashMap;

/**
 * Provides showing of available instruments from
 * console shop.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class ShowItemsCommand implements Command {
    private InstrumentService instrumentService;

    public ShowItemsCommand() {
        this.instrumentService = Context.getInstance().getInstrumentService();
    }

    @Override
    public View execute(String... args) {
        HashMap<String, Instrument> instruments = instrumentService.getInstruments();
        if (instruments.isEmpty()) {
            return new ErrorView(Message.ERROR_MESSAGE_NO_AVAILABLE_INSTRUMENTS);
        }

        return new AvailableInstrumentsView(instruments);
    }
}
