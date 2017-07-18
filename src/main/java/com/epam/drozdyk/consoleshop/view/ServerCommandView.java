package com.epam.drozdyk.consoleshop.view;

/**
 * Views server response.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 17 Apr 2017
 */
public class ServerCommandView extends View {

    public ServerCommandView(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
