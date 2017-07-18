package com.epam.drozdyk.consoleshop.command.impl.server;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.view.ServerCommandView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Provides getting instruments count in console shop.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class GetItemCountCommand implements Command {
    private InstrumentService instrumentService;

    public GetItemCountCommand() {
        this.instrumentService = Context.getInstance().getInstrumentService();
    }

    @Override
    public View execute(String... args) {
        StringBuilder response = new StringBuilder("count:");
        response.append(instrumentService.getInstrumentCount());

        return new ServerCommandView(response.toString());
    }
}
