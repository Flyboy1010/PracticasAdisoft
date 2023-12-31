package app.commands;

import commands.Command;
import students.Manager;
import students.Student;

import java.util.Scanner;

public class AddStudentCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public AddStudentCommand(Manager manager, Scanner input) {
        _manager = manager;
        _input = input;
    }

    @Override
    public void execute() {
        // get name & nia as input

        System.out.print("Introduce new student name: ");
        String name = _input.nextLine();
        System.out.print("Introduce new student NIA: ");
        int nia = Integer.parseInt(_input.nextLine());

        // create student

        Student student = new Student(name, nia);

        // add newly created student

        _manager.addStudent(student);

        // feedback msg

        System.out.print("Student added: ");
        student.print();
    }

    @Override
    public String getDescription() {
        return "adds a new student";
    }
}
