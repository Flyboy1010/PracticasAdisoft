package app.commands;

import commands.Command;
import students.Manager;

public class ListSubjectsCommand implements Command {
    private Manager _manager;

    public ListSubjectsCommand(Manager manager) {
        _manager = manager;
    }

    @Override
    public void execute() {
        _manager.printSubjects();
    }

    @Override
    public String getDescription() {
        return "list all subjects";
    }
}
