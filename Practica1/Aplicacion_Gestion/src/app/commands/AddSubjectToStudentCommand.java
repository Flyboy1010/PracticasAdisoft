package app.commands;

import commands.Command;
import students.Manager;
import students.Student;
import students.Subject;

import java.util.Scanner;

public class AddSubjectToStudentCommand implements Command {
    private Manager _manager;
    private Scanner _input;

    public AddSubjectToStudentCommand(Manager manager, Scanner input) {
        _manager = manager;
        _input = input;
    }

    @Override
    public void execute() {
        // get student nia as input

        System.out.print("Introduce student NIA: ");
        int nia = Integer.parseInt(_input.nextLine());

        // find student

        Student student = _manager.findStudent(nia);

        // check if found

        if (student != null) {
            // pretty print student

            student.prettyPrint();

            // get subject id as input

            System.out.print("Introduce subject ID: ");
            int id = Integer.parseInt(_input.nextLine());

            // find subject

            Subject subject = _manager.findSubject(id);

            // check if found

            if (subject != null) {
                // add it to the student

                boolean success = student.addSubject(subject);

                // feedback msg

                if (success) {
                    System.out.println("Subject added!");
                } else {
                    System.out.println("The student already has that subject!");
                }

            } else {
                System.out.println("Subject not found!");
            }

        } else {
            System.out.println("Student not found!");
        }
    }

    @Override
    public String getDescription() {
        return "adds a subject to a student";
    }
}
