package com.epam.drozdyk.consoleshop.main;

import com.epam.drozdyk.consoleshop.builder.BuilderContainer;
import com.epam.drozdyk.consoleshop.builder.impl.reflect.ReflectInstrumentBuilder;
import com.epam.drozdyk.consoleshop.builder.impl.template.DefaultGuitarBuilder;
import com.epam.drozdyk.consoleshop.builder.impl.template.DefaultViolinBuilder;
import com.epam.drozdyk.consoleshop.command.CommandContainer;
import com.epam.drozdyk.consoleshop.command.CommandController;
import com.epam.drozdyk.consoleshop.command.impl.application.*;
import com.epam.drozdyk.consoleshop.command.impl.server.GetItemCountCommand;
import com.epam.drozdyk.consoleshop.command.impl.server.GetItemInfoCommand;
import com.epam.drozdyk.consoleshop.constant.CommandName;
import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.constant.ServerType;
import com.epam.drozdyk.consoleshop.context.Context;
import com.epam.drozdyk.consoleshop.exception.ApplicationException;
import com.epam.drozdyk.consoleshop.exception.ServerException;
import com.epam.drozdyk.consoleshop.generator.InstrumentGenerator;
import com.epam.drozdyk.consoleshop.generator.factory.InstrumentGeneratorFactory;
import com.epam.drozdyk.consoleshop.model.Guitar;
import com.epam.drozdyk.consoleshop.model.Violin;
import com.epam.drozdyk.consoleshop.server.Server;
import com.epam.drozdyk.consoleshop.util.localization.Localization;
import com.epam.drozdyk.consoleshop.view.View;

import java.util.Scanner;

/**
 * Runs application.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 7 Apr 2017
 */
class AppRunner {
    private final Scanner scanner;

    AppRunner() {
        scanner = new Scanner(System.in);
    }

    void run() {
        startUpServers();
        obtainGenerator();
        obtainLocale();
        final CommandContainer commandContainer = obtainAppCommands();
        final CommandController commandController = new CommandController(commandContainer);
        startDialog(commandContainer, commandController);
    }

    private void startUpServers() {
        CommandContainer container = obtainServerCommands();
        getContext().setServerController(new CommandController(container));
        try {
            new Server(ServerType.TCP, ServerType.TCP.getPort()).start();
            new Server(ServerType.HTTP, ServerType.HTTP.getPort()).start();
        } catch (ServerException e) {
            System.err.print(e.getMessage());
        }
    }

    private CommandContainer obtainServerCommands() {
        CommandContainer container = new CommandContainer();
        container.addCommand(CommandName.GET_ITEM_COUNT_COMMAND, new GetItemCountCommand());
        container.addCommand(CommandName.GET_ITEM_INFO_COMMAND, new GetItemInfoCommand());

        return container;
    }

    private void obtainGenerator() {
        System.out.println(Message.ENTER_INPUT_TYPE);

        initiateBuilderContainer(InstrumentGeneratorFactory.getInstrumentGenerator(scanner));
    }

    private void initiateBuilderContainer(InstrumentGenerator instrumentGenerator) {
        BuilderContainer builderContainer = new BuilderContainer();
        builderContainer.addBuilder("Guitar", new DefaultGuitarBuilder(instrumentGenerator));
        builderContainer.addBuilder("Violin", new DefaultViolinBuilder(instrumentGenerator));
        builderContainer.addBuilder("Guitar-ref", new ReflectInstrumentBuilder(instrumentGenerator, Guitar.class));
        builderContainer.addBuilder("Violin-ref", new ReflectInstrumentBuilder(instrumentGenerator, Violin.class));

        getContext().setBuilderContainer(builderContainer);
    }

    private void obtainLocale() {
        System.out.println(Message.ENTER_COUNTRY);

        String country = scanner.nextLine();
        Localization localization = new Localization(country);
        getContext().setLocalization(localization);
    }

    private CommandContainer obtainAppCommands() {
        CommandContainer container = new CommandContainer();
        container.addCommand(CommandName.ADD_NEW_ITEM_DEFAULT_COMMAND, new AddNewItemDefaultCommand());
        container.addCommand(CommandName.ADD_NEW_ITEM_REFLECT_COMMAND, new AddNewItemReflectCommand());
        container.addCommand(CommandName.ADD_TO_CART_COMMAND, new AddToCartCommand());
        container.addCommand(CommandName.EXIT_COMMAND, new ExitCommand());
        container.addCommand(CommandName.MAKE_ORDER_COMMAND, new MakeOrderCommand());
        container.addCommand(CommandName.SHOW_CART_COMMAND, new ShowCartCommand());
        container.addCommand(CommandName.SHOW_CUSTOM_IN_TIME_SPAN_COMMAND, new ShowCustomInTimeSpanCommand());
        container.addCommand(CommandName.SHOW_ITEMS_COMMAND, new ShowItemsCommand());
        container.addCommand(CommandName.SHOW_LAST_FIVE_COMMAND, new ShowLastFiveCommand());
        container.addCommand(CommandName.SHOW_NEAREST_ORDER_COMMAND, new ShowNearestOrderCommand());

        return container;
    }

    private Context getContext() {
        return Context.getInstance();
    }

    private void startDialog(CommandContainer commandContainer, CommandController commandController) {
        while (true) {
            try {
                System.out.println(commandContainer);
                String userInput = scanner.nextLine();
                View view = commandController.execute(userInput);
                System.out.println(view);
                if (view.getMessage() != null && view.getMessage().equals(Message.MESSAGE_EXIT_MESSAGE)) {
                    break;
                }
            } catch (ApplicationException e) {
                System.err.print(e.getMessage() + "\n");
            }
        }
    }
}
