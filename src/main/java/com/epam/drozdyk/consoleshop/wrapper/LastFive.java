package com.epam.drozdyk.consoleshop.wrapper;

import com.epam.drozdyk.consoleshop.model.Instrument;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Contains last five instruments that are adding to cart.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class LastFive {
    private static int maxSize = 5;
    private HashMap<String, Instrument> lastFiveMap;

    public LastFive() {
        lastFiveMap = new LinkedHashMap<String, Instrument>(maxSize) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Instrument> eldest) {
                return size() > maxSize;
            }
        };
    }

    public HashMap<String, Instrument> getLastFiveMap() {
        return lastFiveMap;
    }

    public void put(String vendorCode, Instrument instrument) {
        lastFiveMap.put(vendorCode, instrument);
    }

    @Override
    public String toString() {
        return getLastFiveMap().toString();
    }
}
