package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.IllegalDateFormatException;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.view.ErrorView;
import com.epam.drozdyk.consoleshop.view.NearestOrderView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.Order;
import com.epam.drozdyk.consoleshop.wrapper.OrderItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowNearestOrderCommandTest {
    private static final String ORDER_DATE = "2017.04.04:00:00";

    @InjectMocks
    private ShowNearestOrderCommand command;
    @Mock
    private OrderService orderService;
    private Order order;

    @Before
    public void setUp() {
        Date date = DateTimeUtil.parse("2017.04.23:14:55");
        OrderItem orderItem = new OrderItem();
        final String vendorCode = "F-100";
        Instrument guitar = new Guitar(1, vendorCode, "ua", 100, 6, GuitarType.ACOUSTIC);
        orderItem.putOrderItem(vendorCode, guitar);
        order = new Order(date, orderItem);
    }

    @Test
    public void execute() {
        when(orderService.getNearestOrder(any())).thenReturn(order);
        final View expected = new NearestOrderView(order);
        final View actual = command.execute(ORDER_DATE);

        assertEquals(expected, actual);
    }

    @Test
    public void executeWithoutAnyOrders() {
        when(orderService.getNearestOrder(any())).thenReturn(null);
        final View expected = new ErrorView(Message.MESSAGE_NO_ORDER);
        final View actual = command.execute(ORDER_DATE);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalUserInputException.class)
    public void executeWithoutParameters() {
        command.execute();
    }

    @Test(expected = IllegalDateFormatException.class)
    public void executeWithIllegalDateFormat() {
        command.execute("illegal format");
    }
}