package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.repository.CartRepository;
import com.epam.drozdyk.consoleshop.wrapper.Cart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultCartServiceTest {
    private static final String GUITAR_VENDOR_CODE = "V-100";
    private static final String VIOLIN_VENDOR_CODE = "V-200";

    @InjectMocks
    private DefaultCartService cartService;
    @Mock
    private CartRepository cartRepository;
    private Cart cart;

    @Before
    public void setUp() {
        cart = new Cart();
        cart.put(GUITAR_VENDOR_CODE, 10);
        cart.put(VIOLIN_VENDOR_CODE, 20);
    }

    @Test
    public void testAddToCart() {
        final String vendorCode = "V-300";
        final int quantity = 30;
        cartService.addInstrumentToCart(vendorCode, quantity);

        verify(cartRepository).addInstrumentToCart(vendorCode, quantity);
    }

    @Test
    public void testGetCart() {
        when(cartRepository.getCart()).thenReturn(cart);
        final Cart actual = cartService.getCart();

        assertEquals(cart, actual);
    }

    @Test
    public void testClearCart() {
        cartService.clearCart();

        verify(cartRepository).clearCart();

        when(cartRepository.getCart()).thenReturn(null);
        final Cart actual = cartService.getCart();

        assertEquals(null, actual);
    }
}
