package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.wrapper.LastFive;

/**
 * Views last five instruments that were added to cart.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class LastFiveView extends View {
    private LastFive lastFive;

    public LastFiveView(LastFive lastFive) {
        this.lastFive = lastFive;
    }

    @Override
    public String toString() {
        return new StringBuilder("Last five instruments in cart:\n")
                .append(lastFive)
                .toString();
    }
}
