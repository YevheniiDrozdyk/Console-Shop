package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.SerializationException;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.util.io.impl.FileInstrumentSerializer;
import com.epam.drozdyk.consoleshop.view.ExitView;
import com.epam.drozdyk.consoleshop.view.View;

import java.io.IOException;

/**
 * Provides exit from console shop.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class ExitCommand implements Command {
    private FileInstrumentSerializer instrumentSerializer;
    private InstrumentService instrumentService;

    public ExitCommand() {
        this.instrumentSerializer = Context.getInstance().getFileInstrumentSerializer();
        this.instrumentService = Context.getInstance().getInstrumentService();
    }

    @Override
    public View execute(String... args) {
        try {
            final int writingCount = 1;
            instrumentSerializer.writeFile(instrumentService.getInstruments(), writingCount);
        } catch (IOException e) {
            throw new SerializationException(Message.ERROR_SERIALIZATION, e.getCause());
        }

        return new ExitView();
    }
}
