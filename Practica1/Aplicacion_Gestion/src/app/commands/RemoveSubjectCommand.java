package app.commands;

import commands.Command;
import students.Manager;
import students.Subject;

import java.util.Scanner;

public class RemoveSubjectCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public RemoveSubjectCommand(Manager manager, Scanner input) {
        _manager = manager;
        _input = input;
    }

    @Override
    public void execute() {
        // get id as input

        System.out.print("Introduce subject ID: ");
        int id = Integer.parseInt(_input.nextLine());

        // find the subject

        Subject subject = _manager.findSubject(id);

        // check if found

        if (subject != null) {
            _manager.removeSubject(subject);

            System.out.println("Subject removed!");
        } else {
            System.out.println("Subject not found!");
        }
    }

    @Override
    public String getDescription() {
        return "removes a subject";
    }
}
