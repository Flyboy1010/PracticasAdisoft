package app.commands;

import commands.Command;
import students.Manager;
import students.Student;

import java.util.Scanner;

public class RemoveStudentCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public RemoveStudentCommand(Manager manager, Scanner input) {
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

        // check if was found

        if (student != null) {
            _manager.removeStudent(student);

            System.out.print("Removed: ");
            student.print();
        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public String getDescription() {
        return "removes student";
    }
}
