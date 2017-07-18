package com.epam.drozdyk.consoleshop.view;

import com.epam.drozdyk.consoleshop.constant.Message;

/**
 * Views farewell message.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class ExitView extends View {

    public ExitView() {
        super(Message.MESSAGE_EXIT_MESSAGE);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
