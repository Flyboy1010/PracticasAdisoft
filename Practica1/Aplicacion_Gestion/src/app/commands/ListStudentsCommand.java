package app.commands;

import commands.Command;
import students.Manager;

public class ListStudentsCommand implements Command {

    private Manager _manager;

    public ListStudentsCommand(Manager manager) {
        _manager = manager;
    }

    @Override
    public void execute() {
        _manager.printStudents();
    }

    @Override
    public String getDescription() {
        return "lists all students";
    }
}
