package com.epam.drozdyk.consoleshop.command.impl.server;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.ErrorView;
import com.epam.drozdyk.consoleshop.view.ServerCommandView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Provides getting instrument info in console shop.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class GetItemInfoCommand implements Command {
    private static final int KEY_INDEX = 0;
    private InstrumentService instrumentService;

    public GetItemInfoCommand() {
        this.instrumentService = Context.getInstance().getInstrumentService();
    }

    @Override
    public View execute(String... args) {
        if (args.length != REQUIRED_LENGTH_1) {
            return new ErrorView(Message.ERROR_ILLEGAL_ARGS);
        }
        String vendorCode = args[KEY_INDEX];
        if (!instrumentService.isInstrumentExist(vendorCode)) {
            return new ErrorView(Message.ERROR_NO_SUCH_INSTRUMENT);
        }
        Instrument instrument = instrumentService.getInstrument(vendorCode);
        String producer = instrument.getProducer();
        int price = instrument.getPrice();
        StringBuilder response = new StringBuilder("producer:");
        response.append(producer);
        response.append(", price:");
        response.append(price);

        return new ServerCommandView(response.toString());
    }
}
