package com.epam.drozdyk.consoleshop.command;

import com.epam.drozdyk.consoleshop.view.View;

/**
 * An abstract command, that takes services from
 * application context and also uses them for making
 * view.
 *
 * @author Yevhenii Drozdyk
 * @version 3.0 22 Apr 2017
 */
public interface Command {
    int REQUIRED_LENGTH_1 = 1;
    int REQUIRED_LENGTH_2 = 2;

    View execute(String... args);
}
