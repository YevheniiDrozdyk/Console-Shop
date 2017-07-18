package com.epam.drozdyk.consoleshop.datasource;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.util.io.InstrumentSerializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Source application instruments.
 *
 * @author Yevhenii Drozdyk
 * @version 3.0 18 Apr 2017
 */
public class InstrumentSource {
    private HashMap<String, Instrument> instruments;

    public InstrumentSource(InstrumentSerializer serializer) {
        marshalInstruments(serializer);
    }

    public Instrument getInstrument(String key) {
        return instruments.get(key);
    }

    public HashMap<String, Instrument> getInstruments() {
        return instruments;
    }

    public void putInstrument(String vendorCode, Instrument instrument) {
        instruments.put(vendorCode, instrument);
    }

    public void putInstruments(HashMap<String, Instrument> instruments) {
        instruments.putAll(instruments);
    }

    public int getInstrumentsCount() {
        return instruments.size();
    }

    private void marshalInstruments(InstrumentSerializer serializer) {
        try {
            instruments = serializer.readFile();
        } catch (IOException e) {
            instruments = new LinkedHashMap<>();
            initiateInstruments();
        }
    }

    private void initiateInstruments() {
        instruments.put("AD810-OP", new Guitar(1, "AD810-OP", "Cort", 2699, 6, GuitarType.ACOUSTIC));
        instruments.put("F310", new Guitar(2, "F310", "Yamaha", 3687, 6, GuitarType.ACOUSTIC));
        instruments.put("DR-100", new Guitar(3, "DR-100", "Epiphone", 3618, 6, GuitarType.ACOUSTIC));
        instruments.put("F370-NAT", new Guitar(4, "F370-NAT", "Yamaha", 6408, 6, GuitarType.ACOUSTIC));
        instruments.put("AD810-BKS", new Guitar(5, "AD810-BKS", "Cort", 3509, 6, GuitarType.ACOUSTIC));

        instruments.put("V5SA-1/8", new Violin(21, "V5SA-1/8", "Yamaha", 13534, 4, ViolinCategory.FACTORY));
        instruments.put("V-100", new Violin(22, "V-100", "Hora", 6656, 4, ViolinCategory.FACTORY));
        instruments.put("V3SKA-3/4", new Violin(23, "V3SKA-3/4", "Yamaha", 6656, 4, ViolinCategory.FACTORY));
        instruments.put("СКМ-V-Antik-G", new Violin(24, "СКМ-V-Antik-G", "СКМ", 61000, 4, ViolinCategory.ARTISANS));
        instruments.put("V-200", new Violin(25, "V-200", "Hora", 11918, 4, ViolinCategory.FACTORY));
    }
}
