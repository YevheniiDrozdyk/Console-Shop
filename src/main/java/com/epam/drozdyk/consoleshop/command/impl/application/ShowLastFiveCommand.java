package com.epam.drozdyk.consoleshop.command.impl.application;

import com.epam.drozdyk.consoleshop.command.Command;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.service.LastFiveService;
import com.epam.drozdyk.consoleshop.view.LastFiveView;
import com.epam.drozdyk.consoleshop.view.View;

/**
 * Provides showing of last five instruments that
 * were added to cart.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 22 Apr 2017
 */
public class ShowLastFiveCommand implements Command {
    private LastFiveService lastFiveService;

    public ShowLastFiveCommand() {
        this.lastFiveService = Context.getInstance().getLastFiveService();
    }

    @Override
    public View execute(String... args) {
        return new LastFiveView(lastFiveService.getLastFive());
    }
}
