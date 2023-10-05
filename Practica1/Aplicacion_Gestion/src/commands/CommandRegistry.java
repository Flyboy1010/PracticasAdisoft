package commands;

import java.util.HashMap;

public class CommandRegistry {
    private HashMap<String, Command> _commands = new HashMap<>();

    public void registerCommand(String commandName, Command command) {
        _commands.put(commandName, command);
    }

    public Command getCommand(String commandName) {
        return _commands.get(commandName);
    }

    public HashMap<String, Command> getCommands() {
        return _commands;
    }
}
