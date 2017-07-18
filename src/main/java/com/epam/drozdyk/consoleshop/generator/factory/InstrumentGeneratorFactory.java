package com.epam.drozdyk.consoleshop.generator.factory;

import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.generator.impl.AutomaticInstrumentGenerator;
import com.epam.drozdyk.consoleshop.generator.impl.ManualInstrumentGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Factory for defining type of instrument creating.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 4 Apr 2017
 */
public class InstrumentGeneratorFactory {
    private static final String GENERATOR_AUTOMATIC = "automatic";
    private static final String GENERATOR_MANUAL = "manual";

    private InstrumentGeneratorFactory() {

    }

    public static InstrumentGenerator getInstrumentGenerator(Scanner scanner) {
        HashMap<String, InstrumentGenerator> generators = obtainGenerators(scanner);

        String key = scanner.nextLine();
        if (generators.get(key) == null) {
            return generators.get(GENERATOR_AUTOMATIC);
        }

        return generators.get(key);
    }

    private static HashMap<String, InstrumentGenerator> obtainGenerators(Scanner scanner) {
        HashMap<String, InstrumentGenerator> generators = new LinkedHashMap<>();
        generators.put(GENERATOR_AUTOMATIC, new AutomaticInstrumentGenerator());
        generators.put(GENERATOR_MANUAL, new ManualInstrumentGenerator(scanner));

        return generators;
    }
}
