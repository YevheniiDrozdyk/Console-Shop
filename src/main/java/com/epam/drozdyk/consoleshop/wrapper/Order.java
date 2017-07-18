package com.epam.drozdyk.consoleshop.wrapper;

import java.util.Date;

/**
 * Contains user order information.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class Order {
    private Date date;
    private OrderItem orderItem;

    public Order(Date date, OrderItem orderItem) {
        this.date = date;
        this.orderItem = orderItem;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder("Date: ");
        order.append(date);
        order.append("\n");
        order.append(orderItem);

        return order.toString();
    }
}
