package com.epam.drozdyk.consoleshop.service;

import com.epam.drozdyk.consoleshop.model.Instrument;
import com.epam.drozdyk.consoleshop.wrapper.Cart;
import com.epam.drozdyk.consoleshop.wrapper.Custom;
import com.epam.drozdyk.consoleshop.wrapper.Order;

import java.util.Date;
import java.util.HashMap;

/**
 * Defines service methods to work with order repository.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 21 Apr 2017
 */
public interface OrderService {

    /**
     * Makes new order with information about its price.
     *
     * @param date date of adding order
     * @param cart user cart
     * @param instruments real instruments source
     * @return total order price
     */
    int makeOrder(Date date, Cart cart, HashMap<String, Instrument> instruments);

    /**
     * Returns orders in time span by fromKey date to toKey date.
     *
     * @param fromKey first date in time span
     * @param toKey last date in time span
     * @return orders in time span
     */
    Custom getOrdersInTimeSpan(Date fromKey, Date toKey);

    /**
     * Returns nearest order in Custom wrapper.
     *
     * @param date date from which the search is performed
     * @return nearest order in Custom wrapper
     */
    Order getNearestOrder(Date date);
}
