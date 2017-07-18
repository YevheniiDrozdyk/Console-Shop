package com.epam.drozdyk.consoleshop.util.localization;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.LackingResourceException;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Provides access to resource bundle.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 7 Apr 2017
 */
public class Localization {
    private ResourceBundle resourceBundle;

    public Localization(String country) {
        resourceBundle = ResourceBundle.getBundle("instrument", new Locale(country));
    }

    public String get(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            throw new LackingResourceException(Message.ERROR_LACKING_RES);
        }
    }
}
