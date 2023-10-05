package app.commands;

import commands.Command;
import students.Manager;
import students.Student;

import java.util.Scanner;

public class FindStudentCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public FindStudentCommand(Manager manager, Scanner input) {
        _manager = manager;
        _input = input;
    }

    @Override
    public void execute() {
        // get nia as input

        System.out.print("Introduce student NIA: ");
        int nia = Integer.parseInt(_input.nextLine());

        // find the student

        Student student = _manager.findStudent(nia);

        // check if it was found

        if (student != null) {
            student.prettyPrint();
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public String getDescription() {
        return "finds student by NIA";
    }
}
