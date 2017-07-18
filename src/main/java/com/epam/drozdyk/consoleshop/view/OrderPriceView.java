package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.constant.Message;

/**
 * Views order's price.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class OrderPriceView extends View {

    public OrderPriceView(int orderPrice) {
        super(new StringBuilder(Message.MESSAGE_ORDER_PRICE)
                .append(orderPrice).toString());
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
