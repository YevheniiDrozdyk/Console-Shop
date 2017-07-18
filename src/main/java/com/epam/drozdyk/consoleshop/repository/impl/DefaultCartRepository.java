package com.epam.drozdyk.consoleshop.repository.impl;

import com.epam.drozdyk.consoleshop.repository.CartRepository;
import com.epam.drozdyk.consoleshop.wrapper.Cart;

/**
 * Default cart repository implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class DefaultCartRepository implements CartRepository {
    private final Cart cart;

    public DefaultCartRepository() {
        cart = new Cart();
    }

    @Override
    public void addInstrumentToCart(String vendorCode, int quantity) {
        cart.put(vendorCode, quantity);
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public void clearCart() {
        cart.clear();
    }
}
