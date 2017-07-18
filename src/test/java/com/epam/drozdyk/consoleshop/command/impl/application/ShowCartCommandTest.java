package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.service.CartService;
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
public class ShowCartCommandTest {
    private static final String GUITAR_VENDOR_CODE = "V-100";
    private static final String VIOLIN_VENDOR_CODE = "V-200";

    @InjectMocks
    private ShowCartCommand command;
    @Mock
    private CartService cartService;
    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
        cart.put(GUITAR_VENDOR_CODE, 10);
        cart.put(VIOLIN_VENDOR_CODE, 20);
    }

    @Test
    public void execute() {
        when(cartService.getCart()).thenReturn(cart);
        final View expected = new CartView(cart);
        final View actual = command.execute();

        assertEquals(expected, actual);
    }
}