package app.commands;

import commands.Command;
import commands.CommandRegistry;

import java.util.HashMap;

public class HelpCommand implements Command {
    private CommandRegistry _commandRegistry;

    public HelpCommand(CommandRegistry commandRegistry) {
        _commandRegistry = commandRegistry;
    }

    @Override
    public void execute() {
        HashMap<String, Command> commands = _commandRegistry.getCommands();

        System.out.println("Command list:");

        for (HashMap.Entry<String, Command> entry : commands.entrySet()) {
            String commandName = entry.getKey();
            Command command = entry.getValue();

            System.out.println("\t\"" + commandName + "\": " + command.getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "shows help";
    }
}
