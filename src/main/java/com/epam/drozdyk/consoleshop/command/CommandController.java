package com.epam.drozdyk.consoleshop.command;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.view.ErrorView;
import com.epam.drozdyk.consoleshop.view.View;

import java.util.Arrays;
import java.util.Map;

/**
 * Provides command executions by their names.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 4 Apr 2017
 */
public class CommandController {
    private static final int COMMAND_INDEX = 0;
    private static final int FIRST_ARG_INDEX = 1;
    private final CommandContainer commandContainer;

    public CommandController(CommandContainer commandContainer) {
        this.commandContainer = commandContainer;
    }

    public View execute(String... input) {
        String[] args = input[COMMAND_INDEX].split(" ");
        Map<String, Command> commands = commandContainer.getCommands();
        Command command = commands.get(args[COMMAND_INDEX]);
        if (command != null) {
            return command.execute(Arrays.copyOfRange(args, FIRST_ARG_INDEX, args.length));
        }

        return new ErrorView(Message.ERROR_MESSAGE_NO_SUCH_COMMANDS);
    }
}
