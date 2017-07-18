package com.epam.drozdyk.consoleshop.generator.impl;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.GeneratingException;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.util.localization.Localization;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Sets the manual type for instrument creating.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 4 Apr 2017
 */
public class ManualInstrumentGenerator implements InstrumentGenerator {
    private Scanner scanner;

    public ManualInstrumentGenerator(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int generateInt(String message) {
        printMessage(message);

        int value;
        try {
            value = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new GeneratingException(Message.ERROR_ILLEGAL_ARGUMENT, e);
        }
        scanner.nextLine();

        return value;
    }

    @Override
    public String generateString(String message) {
        printMessage(message);

        return scanner.nextLine();
    }

    @Override
    public GuitarType generateGuitarType(String message) {
        printMessage(message);

        try {
            String line = scanner.nextLine();

            return GuitarType.valueOf(line.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new GeneratingException(Message.ERROR_ILLEGAL_ARGUMENT, e);
        }
    }

    @Override
    public ViolinCategory generateViolinCategory(String message) {
        printMessage(message);

        try {
            String line = scanner.nextLine();

            return ViolinCategory.valueOf(line.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new GeneratingException(Message.ERROR_ILLEGAL_ARGUMENT, e);
        }
    }

    private Localization getLocalization() {
        return Context.getInstance().getLocalization();
    }

    private void printMessage(String message) {
        String localizeMessage = getLocalization().get(message);

        System.out.println("Write " + localizeMessage + ":");
    }
}
