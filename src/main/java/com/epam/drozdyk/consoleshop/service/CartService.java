package com.epam.drozdyk.consoleshop.service;

import com.epam.drozdyk.consoleshop.wrapper.Cart;

/**
 * Defines service methods to work with cart repository.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public interface CartService {

    /**
     * Adds instrument to cart.
     *
     * @param vendorCode vendor code of adding instrument
     * @param quantity   quantity of instruments
     */
    void addInstrumentToCart(String vendorCode, int quantity);

    /**
     * Returns user's cart object.
     *
     * @return cart object
     */
    Cart getCart();

    /**
     * Clears user's cart.
     */
    void clearCart();
}
