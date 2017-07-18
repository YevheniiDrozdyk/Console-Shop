package com.epam.drozdyk.consoleshop.wrapper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Contains user cart of instruments.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class Cart {
    private HashMap<String, Integer> cartMap;

    public Cart() {
        cartMap = new LinkedHashMap<>();
    }

    public HashMap<String, Integer> getCartMap() {
        return cartMap;
    }

    public void put(String vendorCode, int quantity) {
        cartMap.put(vendorCode, quantity);
    }

    public void clear() {
        cartMap.clear();
    }

    @Override
    public String toString() {
        return "Cart:\n" + getCartMap();
    }
}
