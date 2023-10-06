package app.commands;

import commands.Command;
import students.Manager;
import students.Subject;

import java.util.Scanner;

public class AddSubjectCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public AddSubjectCommand(Manager manager, Scanner input) {
        _manager = manager;
        _input = input;
    }

    @Override
    public void execute() {
        // get name & nia as input

        System.out.print("Introduce new subject name: ");
        String name = _input.nextLine();
        System.out.print("Introduce new subject ID: ");
        int id = Integer.parseInt(_input.nextLine());

        // create subject

        Subject subject = new Subject(name, id);

        // add newly created subject

        _manager.addSubject(subject);

        // feedback msg

        System.out.print("Subject added: ");
        subject.print();
    }

    @Override
    public String getDescription() {
        return "adds a new subject";
    }
}
