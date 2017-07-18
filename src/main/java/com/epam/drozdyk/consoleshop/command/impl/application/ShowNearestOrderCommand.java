package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.view.ErrorView;
import com.epam.drozdyk.consoleshop.view.NearestOrderView;
import com.epam.drozdyk.consoleshop.view.View;
import com.epam.drozdyk.consoleshop.wrapper.Order;

import java.util.Date;

/**
 * Provides showing of nearest order that
 * was made in console shop, by its date.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class ShowNearestOrderCommand implements Command {
    private static final int DATE_INDEX = 0;
    private OrderService orderService;

    public ShowNearestOrderCommand() {
        this.orderService = Context.getInstance().getOrderService();
    }

    @Override
    public View execute(String... args) {
        if (args.length != REQUIRED_LENGTH_1) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_ARGS);
        }
        Date nearest = DateTimeUtil.parse(args[DATE_INDEX]);
        Order nearestOrder = orderService.getNearestOrder(nearest);
        if (nearestOrder == null) {
            return new ErrorView(Message.MESSAGE_NO_ORDER);
        }

        return new NearestOrderView(nearestOrder);
    }
}
