package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.model.Instrument;

import java.util.HashMap;
import java.util.Map;

/**
 * Views available instruments.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class AvailableInstrumentsView extends View {
    private HashMap<String, Instrument> instruments;

    public AvailableInstrumentsView(HashMap<String, Instrument> instruments) {
        this.instruments = instruments;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Available instruments:\n");
        for (Object o : instruments.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            result.append(pair.getValue()).append("\n");
        }

        return result.toString();
    }
}
