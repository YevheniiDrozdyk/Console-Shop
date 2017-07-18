package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.exception.NoSuchInstrumentException;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.service.LastFiveService;
import com.epam.drozdyk.consoleshop.view.CartView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Provides adding instrument to cart (only existing items).
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class AddToCartCommand implements Command {
    private static final int KEY_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    private InstrumentService instrumentService;
    private CartService cartService;
    private LastFiveService lastFiveService;

    public AddToCartCommand() {
        this.instrumentService = Context.getInstance().getInstrumentService();
        this.cartService = Context.getInstance().getCartService();
        this.lastFiveService = Context.getInstance().getLastFiveService();
    }

    @Override
    public View execute(String... args) {
        String vendorCode;
        try {
            vendorCode = args[KEY_INDEX];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_ARGS);
        }
        int quantity = 1;
        if (args.length == REQUIRED_LENGTH_2) {
            quantity = Integer.parseInt(args[QUANTITY_INDEX]);
        }
        if (quantity < 1) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_QUANTITY);
        }
        if (!instrumentService.isInstrumentExist(vendorCode)) {
            throw new NoSuchInstrumentException(Message.ERROR_NO_SUCH_INSTRUMENT);
        }
        cartService.addInstrumentToCart(vendorCode, quantity);
        Instrument instrument = instrumentService.getInstrument(vendorCode);
        lastFiveService.putInstrumentToLastFive(vendorCode, instrument);

        return new CartView(cartService.getCart());
    }
}
