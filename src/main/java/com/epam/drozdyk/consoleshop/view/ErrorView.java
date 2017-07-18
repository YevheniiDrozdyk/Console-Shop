package com.epam.drozdyk.consoleshop.view;

/**
 * Views error message.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 30 Mar 2017
 */
public class ErrorView extends View {

    public ErrorView(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
