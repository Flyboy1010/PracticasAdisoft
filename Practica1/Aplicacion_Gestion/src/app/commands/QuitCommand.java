package app.commands;

import app.App;
import commands.Command;

public class QuitCommand implements Command {
    private App _app;

    public QuitCommand(App app) {
        _app = app;
    }

    @Override
    public void execute() {
        _app.stop();
    }

    @Override
    public String getDescription() {
        return "quits the application";
    }
}
