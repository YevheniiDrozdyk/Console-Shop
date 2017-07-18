package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.wrapper.Custom;

/**
 * Views orders in time span.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class TimeSpanOrdersView extends View {
    private Custom custom;

    public TimeSpanOrdersView(Custom custom) {
        this.custom = custom;
    }

    @Override
    public String toString() {
        return custom.toString();
    }
}
