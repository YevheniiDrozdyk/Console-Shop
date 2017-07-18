package com.epam.drozdyk.consoleshop.repository.impl;

import com.epam.drozdyk.consoleshop.repository.OrderRepository;
import com.epam.drozdyk.consoleshop.wrapper.Custom;
import com.epam.drozdyk.consoleshop.wrapper.Order;

import java.util.Date;
import java.util.Map;

/**
 * Default order repository implementation.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 21 Apr 2017
 */
public class DefaultOrderRepository implements OrderRepository {
    private final Custom custom;

    public DefaultOrderRepository() {
        custom = new Custom();
    }

    @Override
    public void addOrder(Date date, Order order) {
        custom.put(date, order);
    }

    @Override
    public Custom getOrdersInTimeSpan(Date fromKey, Date toKey) {
        return new Custom(custom.subMap(fromKey, toKey));
    }

    @Override
    public Order getNearestOrder(Date date) {
        Map.Entry<Date, Order> lower = custom.getCustom().floorEntry(date);
        Map.Entry<Date, Order> higher = custom.getCustom().ceilingEntry(date);
        if (lower == null && higher == null) {
            return null;
        }
        if (lower == null) {
            return custom.getOrder(higher.getKey());
        }
        if (higher == null) {
            return custom.getOrder(lower.getKey());
        }
        long lowerDifference = Math.abs(lower.getKey().getTime() - date.getTime());
        long higherDifference = Math.abs(higher.getKey().getTime() - date.getTime());

        return lowerDifference < higherDifference ? custom.getOrder(lower.getKey()) : custom.getOrder(higher.getKey());
    }
}
