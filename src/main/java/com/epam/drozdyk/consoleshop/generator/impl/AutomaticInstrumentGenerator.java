package com.epam.drozdyk.consoleshop.generator.impl;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;

import java.util.Random;

/**
 * Sets the automatic type for instrument creating.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 4 Apr 2017
 */
public class AutomaticInstrumentGenerator implements InstrumentGenerator {
    private final Random random;
    private final int RANDOM_BOUND = 10000;

    public AutomaticInstrumentGenerator() {
        random = new Random();
    }

    @Override
    public int generateInt(String message) {
        return random.nextInt(RANDOM_BOUND);
    }

    @Override
    public String generateString(String message) {
        return message + random.nextInt(RANDOM_BOUND);
    }

    @Override
    public GuitarType generateGuitarType(String message) {
        int bound = GuitarType.values().length;

        return GuitarType.getGuitarType(random.nextInt(bound));
    }

    @Override
    public ViolinCategory generateViolinCategory(String message) {
        int bound = ViolinCategory.values().length;

        return ViolinCategory.getViolinCategory(random.nextInt(bound));

    }
}
