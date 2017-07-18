package com.epam.drozdyk.consoleshop.service.impl;

import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.repository.OrderRepository;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.wrapper.Cart;
import com.epam.drozdyk.consoleshop.wrapper.Custom;
import com.epam.drozdyk.consoleshop.wrapper.Order;
import com.epam.drozdyk.consoleshop.wrapper.OrderItem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Default order service implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 19 Apr 2017
 */
public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public int makeOrder(Date date, Cart cart, HashMap<String, Instrument> instruments) {
        return checkout(date, cart, instruments);
    }

    @Override
    public Custom getOrdersInTimeSpan(Date fromKey, Date toKey) {
        return orderRepository.getOrdersInTimeSpan(fromKey, toKey);
    }

    @Override
    public Order getNearestOrder(Date date) {
        return orderRepository.getNearestOrder(date);
    }

    private int checkout(Date date, Cart cart, HashMap<String, Instrument> instruments) {
        int orderPrice = 0;
        OrderItem orderItem = new OrderItem();
        for (Object cartItem : cart.getCartMap().entrySet()) {
            Map.Entry itemEntry = (Map.Entry) cartItem;
            String vendorCode = (String) itemEntry.getKey();
            int quantity = (int) itemEntry.getValue();
            Instrument instrument = instruments.get(vendorCode);
            orderPrice += calculateOrderItemPrice(instrument, quantity, orderItem);
        }
        Order order = new Order(date, orderItem);
        orderRepository.addOrder(date, order);

        return orderPrice;
    }

    private int calculateOrderItemPrice(Instrument instrument, int quantity, OrderItem orderItem) {
        orderItem.putOrderItem(instrument.getVendorCode(), instrument);

        return instrument.getPrice() * quantity;
    }
}
