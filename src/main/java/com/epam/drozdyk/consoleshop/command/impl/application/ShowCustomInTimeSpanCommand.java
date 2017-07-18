package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.IllegalUserInputException;
import com.epam.drozdyk.consoleshop.service.OrderService;
import com.epam.drozdyk.consoleshop.util.date.DateTimeUtil;
import com.epam.drozdyk.consoleshop.view.TimeSpanOrdersView;
import com.epam.drozdyk.consoleshop.view.View;

import java.util.Date;

/**
 * Provides showing orders that were made in
 * console shop.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class ShowCustomInTimeSpanCommand implements Command {
    private static final int FROM_DATE_INDEX = 0;
    private static final int TO_DATE_INDEX = 1;

    private OrderService orderService;

    public ShowCustomInTimeSpanCommand() {
        this.orderService = Context.getInstance().getOrderService();
    }

    @Override
    public View execute(String... args) {
        if (args.length != REQUIRED_LENGTH_2) {
            throw new IllegalUserInputException(Message.ERROR_ILLEGAL_ARGS);
        }
        Date from = DateTimeUtil.parse(args[FROM_DATE_INDEX]);
        Date to = DateTimeUtil.parse(args[TO_DATE_INDEX]);

        return new TimeSpanOrdersView(orderService.getOrdersInTimeSpan(from, to));
    }
}
