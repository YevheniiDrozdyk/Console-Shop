package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.exception.NoSuchInstrumentException;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.service.LastFiveService;
import com.epam.drozdyk.consoleshop.view.CartView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.Cart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddToCartCommandTest {
    private static final String GUITAR_VENDOR_CODE = "V-100";
    private static final String VIOLIN_VENDOR_CODE = "V-200";

    @InjectMocks
    private AddToCartCommand command;
    @Mock
    private InstrumentService instrumentService;
    @Mock
    private CartService cartService;

    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
        cart.put(VIOLIN_VENDOR_CODE, 1);
    }

    @Test
    public void executeWithQuantityArgument() {
        final View expected = new CartView(cart);
        when(instrumentService.isInstrumentExist(GUITAR_VENDOR_CODE)).thenReturn(true);
        when(cartService.getCart()).thenReturn(cart);
        final View actual = command.execute(GUITAR_VENDOR_CODE, "2");

        assertEquals(expected, actual);
    }

    @Test
    public void executeWithoutQuantityArgument() {
        final View expected = new CartView(cart);
        when(instrumentService.isInstrumentExist(GUITAR_VENDOR_CODE)).thenReturn(true);
        when(cartService.getCart()).thenReturn(cart);
        final View actual = command.execute(GUITAR_VENDOR_CODE);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalUserInputException.class)
    public void executeWithoutArguments() {
        command.execute();
    }

    @Test(expected = IllegalUserInputException.class)
    public void executeWithIllegalQuantityArgument() {
        when(instrumentService.isInstrumentExist(GUITAR_VENDOR_CODE)).thenReturn(true);
        when(cartService.getCart()).thenReturn(cart);
        command.execute(GUITAR_VENDOR_CODE, "-1");
    }

    @Test(expected = NoSuchInstrumentException.class)
    public void executeWithNonexistentItemArgument() {
        when(instrumentService.isInstrumentExist(GUITAR_VENDOR_CODE)).thenReturn(false);
        when(cartService.getCart()).thenReturn(cart);
        command.execute(GUITAR_VENDOR_CODE);
    }
}