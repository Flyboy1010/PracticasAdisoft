package app.commands;

import commands.Command;
import students.Manager;

public class SaveCommand implements Command {
    public Manager _manager;

    public SaveCommand(Manager manager) {
        _manager = manager;
    }

    @Override
    public void execute() {
        _manager.saveStudentsFile("data/students.txt");
        _manager.saveSubjectsFile("data/subjects.txt");

        System.out.println("Save done!");
    }

    @Override
    public String getDescription() {
        return "saves students & subjects file";
    }
}
