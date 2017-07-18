package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.exception.IllegalDateFormatException;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.service.CartService;
import com.epam.drozdyk.consoleshop.service.InstrumentService;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.view.OrderPriceView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.Cart;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MakeOrderCommandTest {
    private final String ORDER_DATE = "2017.04.01:12:00";

    @InjectMocks
    private MakeOrderCommand command;
    @Mock
    private CartService cartService;
    @Mock
    private InstrumentService instrumentService;
    @Mock
    private OrderService orderService;
    private Cart cart;
    private HashMap<String, Instrument> instruments;

    @Before
    public void setUp() {
        Instrument guitar = new Guitar(1, "F-100", "ua", 100, 3, GuitarType.BASS);
        Instrument violin = new Violin(2, "F-200", "ru", 200, 4, ViolinCategory.ARTISANS);
        instruments = new LinkedHashMap<>();
        instruments.put("F-100", guitar);
        instruments.put("F-200", violin);
        cart = new Cart();
        cart.put("F-100", 1);
        cart.put("F-200", 2);
    }

    @Test
    public void execute() {
        final int orderPrice = 500;
        when(cartService.getCart()).thenReturn(cart);
        when(instrumentService.getInstruments()).thenReturn(instruments);
        when(orderService.makeOrder(DateTimeUtil.parse(ORDER_DATE), cart, instruments)).thenReturn(orderPrice);

        final View expected = new OrderPriceView(orderPrice);
        final View actual = command.execute(ORDER_DATE);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalUserInputException.class)
    public void executeWithoutParameters() {
        command.execute();
    }

    @Test(expected = IllegalDateFormatException.class)
    public void executeWithIllegalDateFormat() {
        command.execute("03.04.2015 00:00");
    }
}