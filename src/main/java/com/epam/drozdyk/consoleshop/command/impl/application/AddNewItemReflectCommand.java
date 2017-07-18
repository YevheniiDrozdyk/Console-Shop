package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.view.AvailableInstrumentsView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Reflection command implementation for adding a
 * new item to instrument source.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class AddNewItemReflectCommand extends AddNewItemCommand {

    @Override
    public View execute(String... args) {
        if (args.length != REQUIRED_LENGTH_1) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_ARGS);
        }
        StringBuilder builderKey = new StringBuilder();
        builderKey.append(args[BUILDER_INDEX].trim());
        builderKey.append("-ref");
        addNewInstrument(builderKey.toString());

        return new AvailableInstrumentsView(instrumentService.getInstruments());
    }
}
