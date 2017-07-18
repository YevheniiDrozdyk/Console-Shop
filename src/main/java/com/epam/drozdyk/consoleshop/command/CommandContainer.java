package com.epam.drozdyk.consoleshop.command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Can be contained application and server commands.
 *
 * @author Yevhenii Drozdyk
 * @version 3.0 18 Apr 2017
 */
public class CommandContainer {
    private Map<String, Command> commands;

    public CommandContainer() {
        commands = new LinkedHashMap<>();
    }

    public void addCommand(String key, Command command) {
        commands.put(key, command);
    }

    Map<String, Command> getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        StringBuilder container = new StringBuilder("Available commands:\n");
        int number = 1;
        for (String key : commands.keySet()) {
            container.append(number++);
            container.append(") ");
            container.append(key);
            container.append(";\n");
        }

        return container.toString();
    }
}
