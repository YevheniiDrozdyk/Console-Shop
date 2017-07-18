package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.wrapper.Cart;

/**
 * Views user's cart.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class CartView extends View {
    private Cart cart;

    public CartView(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return cart.toString();
    }
}
