package com.epam.drozdyk.consoleshop.generator;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;

/**
 * Generates values for instrument fields.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 4 Apr 2017
 */
public interface InstrumentGenerator {

    int generateInt(String message);

    String generateString(String message);

    GuitarType generateGuitarType(String message);

    ViolinCategory generateViolinCategory(String message);
}
