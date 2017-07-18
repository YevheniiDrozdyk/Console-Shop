package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.view.OrderPriceView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.Cart;

import java.util.Date;
import java.util.HashMap;

/**
 * Provides making of new order with fixing of
 * its date and instrument list.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class MakeOrderCommand implements Command {
    private static final int DATE_INDEX = 0;

    private CartService cartService;
    private InstrumentService instrumentService;
    private OrderService orderService;

    public MakeOrderCommand() {
        this.cartService = Context.getInstance().getCartService();
        this.instrumentService = Context.getInstance().getInstrumentService();
        this.orderService = Context.getInstance().getOrderService();
    }

    @Override
    public View execute(String... args) {
        if (args.length != REQUIRED_LENGTH_1) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_ARGS);
        }
        Date orderDate = DateTimeUtil.parse(args[DATE_INDEX]);
        Cart cart = cartService.getCart();
        HashMap<String, Instrument> instruments = instrumentService.getInstruments();
        int orderPrice = orderService.makeOrder(orderDate, cart, instruments);
        cartService.clearCart();

        return new OrderPriceView(orderPrice);
    }
}
