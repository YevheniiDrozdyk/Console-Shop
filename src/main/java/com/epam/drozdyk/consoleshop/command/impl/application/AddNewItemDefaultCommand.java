package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.view.AvailableInstrumentsView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Default command implementation for adding a new
 * item to instrument source.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class AddNewItemDefaultCommand extends AddNewItemCommand {

    @Override
    public View execute(String... args) {
        if (args.length != REQUIRED_LENGTH_1) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_ARGS);
        }
        String builderKey = args[BUILDER_INDEX];
        addNewInstrument(builderKey);

        return new AvailableInstrumentsView(instrumentService.getInstruments());
    }
}
