package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.constant.GuitarType;
import com.epam.drozdyk.consoleshop.constant.ViolinCategory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.repository.OrderRepository;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.wrapper.Cart;
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
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultOrderServiceTest {

    @InjectMocks
    private DefaultOrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    private HashMap<String, Instrument> instruments;
    private Cart cart;
    private Order order;
    private Custom custom;

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

        OrderItem orderItem = new OrderItem();
        orderItem.putOrderItem("F-100", guitar);
        orderItem.putOrderItem("F-200", violin);
        Date firstDate = DateTimeUtil.parse("2017.04.01:12:00");
        order = new Order(firstDate, orderItem);

        Instrument guitar2 = new Guitar(3, "F-300", "de", 300, 5, GuitarType.ACOUSTIC);
        OrderItem orderItem2 = new OrderItem();
        orderItem.putOrderItem("F-300", guitar2);
        Date secondDate = DateTimeUtil.parse("2017.06.01:12:00");
        Order order2 = new Order(secondDate, orderItem2);

        custom = new Custom();
        custom.put(firstDate, order);
        custom.put(secondDate, order2);
    }

    @Test
    public void makeOrderWithTruePrice() {
        Date orderDate = DateTimeUtil.parse("2017.04.23:11:00");
        final int expected = 500;
        final int actual = orderService.makeOrder(orderDate, cart, instruments);

        assertEquals(expected, actual);
    }

    @Test
    public void makeOrderWithFalsePrice() {
        Date orderDate = DateTimeUtil.parse("2015.11.03:12:00");
        final int expected = 1000;
        final int actual = orderService.makeOrder(orderDate, cart, instruments);

        assertNotEquals(expected, actual);
    }

    @Test
    public void getOrdersInTimeSpan() {
        Date fromDate = DateTimeUtil.parse("2017.03.01:12:00");
        Date toDate = DateTimeUtil.parse("2017.08.01:00:00");
        when(orderRepository.getOrdersInTimeSpan(fromDate, toDate)).thenReturn(custom);
        final Custom actual = orderService.getOrdersInTimeSpan(fromDate, toDate);

        assertEquals(custom, actual);
    }

    @Test
    public void getNearestOrder() {
        Date date = DateTimeUtil.parse("2017.04.01:10:00");
        when(orderRepository.getNearestOrder(date)).thenReturn(order);
        final Order actual = orderService.getNearestOrder(date);

        assertEquals(order, actual);
    }
}