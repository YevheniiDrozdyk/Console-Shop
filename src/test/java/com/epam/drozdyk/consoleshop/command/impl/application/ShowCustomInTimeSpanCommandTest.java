package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.exception.IllegalDateFormatException;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.view.TimeSpanOrdersView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.Custom;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowCustomInTimeSpanCommandTest {
    @InjectMocks
    private ShowCustomInTimeSpanCommand command;
    @Mock
    private OrderService orderService;
    private Custom custom;

    @Before
    public void setUp() {
        Instrument guitar = new Guitar(1, "F-100", "ua", 100, 3, GuitarType.BASS);
        Instrument violin = new Violin(2, "F-200", "ru", 200, 4, ViolinCategory.ARTISANS);

        OrderItem orderItem1 = new OrderItem();
        orderItem1.putOrderItem("F-100", guitar);
        orderItem1.putOrderItem("F-200", violin);
        Date firstDate = DateTimeUtil.parse("2017.04.01:12:00");
        Order order1 = new Order(firstDate, orderItem1);

        Instrument guitar2 = new Guitar(3, "F-300", "de", 300, 5, GuitarType.ACOUSTIC);
        OrderItem orderItem2 = new OrderItem();
        orderItem1.putOrderItem("F-300", guitar2);
        Date secondDate = DateTimeUtil.parse("2017.06.01:12:00");
        Order order2 = new Order(secondDate, orderItem2);

        custom = new Custom();
        custom.put(firstDate, order1);
        custom.put(secondDate, order2);
    }

    @Test
    public void execute() {
        when(orderService.getOrdersInTimeSpan(any(), any())).thenReturn(custom);
        final View expected = new TimeSpanOrdersView(custom);
        final View actual = command.execute("2017.01.01:12:00", "2017.12.31:00:00");

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalUserInputException.class)
    public void executeWithoutParameters() {
        command.execute();
    }

    @Test(expected = IllegalDateFormatException.class)
    public void executeWithIllegalDateFormat() {
        command.execute("03.04.2015 00:00", "20.03.2017 20:00");
    }
}