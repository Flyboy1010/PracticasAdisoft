package app.commands;

import commands.Command;
import students.Manager;
import students.Subject;

import java.util.Scanner;

public class FindSubjectCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public FindSubjectCommand(Manager manager, Scanner input) {
        _manager = manager;
        _input = input;
    }

    @Override
    public void execute() {
        // get nia as input

        System.out.print("Introduce subject ID: ");
        int id = Integer.parseInt(_input.nextLine());

        // find the student

        Subject subject = _manager.findSubject(id);

        // check if it was found

        if (subject != null) {
            subject.prettyPrint();
        } else {
            System.out.println("Subject not found!");
        }
    }

    @Override
    public String getDescription() {
        return "finds subject by ID";
    }
}
