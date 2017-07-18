package com.epam.drozdyk.consoleshop.repository;

import com.epam.drozdyk.consoleshop.wrapper.Custom;
import com.epam.drozdyk.consoleshop.wrapper.Order;

import java.util.Date;

/**
 * Defines methods for work with custom wrapper.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 21 Apr 2017
 */
public interface OrderRepository {

    /**
     * Adds new order to Custom wrapper.
     *
     * @param date date of adding order
     * @param order order for adding
     */
    void addOrder(Date date, Order order);

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
