package com.epam.drozdyk.consoleshop.wrapper;

import com.epam.drozdyk.consoleshop.model.Instrument;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Contains map of instruments that can be added to order.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class OrderItem {
    private HashMap<String, Instrument> orderItemMap;

    public OrderItem() {
        orderItemMap = new LinkedHashMap<>();
    }

    public void putOrderItem(String vendorCode, Instrument instrument) {
        orderItemMap.put(vendorCode, instrument);
    }

    @Override
    public String toString() {
        StringBuilder items = new StringBuilder();
        for (Object orderItem : orderItemMap.entrySet()) {
            Map.Entry orderItemEntry = (Map.Entry) orderItem;
            items.append("Vendor code: ");
            items.append(orderItemEntry.getKey());
            items.append("\nInstrument: ");
            items.append(orderItemEntry.getValue());
        }

        return items.toString();
    }
}
