package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.view.CartView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Provides showing of user cart with vendor
 * codes and quantities of it.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class ShowCartCommand implements Command {
    private CartService cartService;

    public ShowCartCommand() {
        this.cartService = Context.getInstance().getCartService();
    }

    @Override
    public View execute(String... args) {
        return new CartView(cartService.getCart());
    }
}
