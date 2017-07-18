package com.epam.drozdyk.consoleshop.wrapper;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * Contains information about all orders in application.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class Custom {
    private NavigableMap<Date, Order> custom;

    public Custom() {
        custom = new TreeMap<>();
    }

    public Custom(NavigableMap<Date, Order> custom) {
        this.custom = custom;
    }

    public void put(Date date, Order order) {
        custom.put(date, order);
    }

    public NavigableMap<Date, Order> subMap(Date fromKey, Date toKey) {
        return custom.subMap(fromKey, true, toKey, true);
    }

    public NavigableMap<Date, Order> getCustom() {
        return custom;
    }

    public Order getOrder(Date date) {
        return custom.get(date);
    }

    @Override
    public String toString() {
        StringBuilder orders = new StringBuilder("Orders:\n");
        for (Object order : custom.entrySet()) {
            Map.Entry orderEntry = (Map.Entry) order;
            orders.append("Order: ");
            orders.append(orderEntry.getValue());
        }

        return orders.toString();
    }
}
