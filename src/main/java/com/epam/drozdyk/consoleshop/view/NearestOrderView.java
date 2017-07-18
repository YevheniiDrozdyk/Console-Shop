package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.wrapper.Order;

/**
 * Views nearest order.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class NearestOrderView extends View {
    private Order nearestOrder;

    public NearestOrderView(Order nearestOrder) {
        this.nearestOrder = nearestOrder;
    }

    @Override
    public String toString() {
        return new StringBuilder("Nearest order:\n")
                .append(nearestOrder)
                .toString();
    }
}
