package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.repository.CartRepository;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.wrapper.Cart;

/**
 * Default cart service implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 19 Apr 2017
 */
public class DefaultCartService implements CartService {
    private final CartRepository cartRepository;

    public DefaultCartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void addInstrumentToCart(String vendorCode, int quantity) {
        cartRepository.addInstrumentToCart(vendorCode, quantity);
    }

    @Override
    public Cart getCart() {
        return cartRepository.getCart();
    }

    @Override
    public void clearCart() {
        cartRepository.clearCart();
    }
}
