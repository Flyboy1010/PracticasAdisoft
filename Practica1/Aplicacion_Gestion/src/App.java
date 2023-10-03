// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import students.Manager;
import students.Student;
import students.Subject;

import java.util.Scanner;

public class App {

    private static final String COMMAND_HELP = "h";
    private static final String COMMAND_LIST = "l";
    private static final String COMMAND_FIND = "f";
    private static final String COMMAND_ADD = "a";
    private static final String COMMAND_REMOVE = "r";
    private static final String COMMAND_ADD_SUBJECT_TO_STUDENT = "m";
    private static final String COMMAND_REMOVE_SUBJECT_FROM_STUDENT = "d";
    private static final String COMMAND_SAVE = "s";
    private static final String COMMAND_QUIT = "q";
    private static final String COMMAND_LIST_SUBJECTS = "ls";
    private static final String COMMAND_FIND_SUBJECT = "fs";

    private Scanner _input = new Scanner(System.in);
    private Manager _manager = new Manager();
    private boolean _running = true;

    public App() {
        // load subject & student files, (the order is important, do not change)

        _manager.readSubjectsFile("data/subjects.txt");
        _manager.readStudentsFile("data/students.txt");
    }

    private void printHelp() {
        // prints all the commands

        System.out.println("Command list:");
        System.out.println("\t'l'  lists all students");
        System.out.println("\t'f'  finds student by NIA");
        System.out.println("\t'a'  adds student");
        System.out.println("\t'r'  removes student");
        System.out.println("\t'm'  adds subject to student");
        System.out.println("\t'd'  removes subject from student");
        System.out.println("\t'ls' lists all subjects");
        System.out.println("\t'fs' finds subject by ID");
        System.out.println("\t's'  saves modifications");
        System.out.println("\t'q'  quits");
    }

    private void promptFindStudent() {
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

    private void promptAddStudent() {
        // get name & nia as input

        System.out.print("Introduce new student name: ");
        String name = _input.nextLine();
        System.out.print("Introduce new student NIA: ");
        int nia = Integer.parseInt(_input.nextLine());

        // add newly created student

        _manager.addStudent(new Student(name, nia));
    }

    private void promptRemoveStudent() {
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

    private void promptAddSubjectToStudent() {
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

    private void promptRemoveSubjectFromStudent() {
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

                boolean success = student.removeSubject(subject);

                // feedback msg

                if (success) {
                    System.out.println("Subject removed!");
                } else {
                    System.out.println("The student doesn't have that suject");
                }

            } else {
                System.out.println("Subject not found!");
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    private void promptFindSubject() {
        // get id as input

        System.out.print("Introduce subject id: ");
        int id = Integer.parseInt(_input.nextLine());

        // find subject

        Subject subject = _manager.findSubject(id);

        if (subject != null) {
            subject.prettyPrint();
        } else {
            System.out.println("Subject not found");
        }
    }

    private void promptSelectAction() {
        // get command as input

        System.out.print("Introduce command, 'h' for help: ");
        String command = _input.nextLine().toLowerCase();

        // action for each command

        switch (command) {
            case COMMAND_HELP:
                printHelp();
                break;
            case COMMAND_LIST:
                _manager.printStudents();
                break;
            case COMMAND_FIND:
                promptFindStudent();
                break;
            case COMMAND_ADD:
                promptAddStudent();
                break;
            case COMMAND_REMOVE:
                promptRemoveStudent();
                break;
            case COMMAND_ADD_SUBJECT_TO_STUDENT:
                promptAddSubjectToStudent();
                break;
            case COMMAND_REMOVE_SUBJECT_FROM_STUDENT:
                promptRemoveSubjectFromStudent();
                break;
            case COMMAND_LIST_SUBJECTS:
                _manager.printSubjects();
                break;
            case COMMAND_FIND_SUBJECT:
                promptFindSubject();
                break;
            case COMMAND_SAVE:
                _manager.saveSubjectsFile("data/subjects.txt");
                _manager.saveStudentsFile("data/students.txt");
                break;
            case COMMAND_QUIT:
                _running = false;
                break;
            default:
                System.out.println("Command not found");
        }
    }

    private void update() {
        while (_running) {
            promptSelectAction();
        }
    }

    public static void main(String[] args) {
        // create App instance

        App app = new App();

        // update the app

        app.update();
    }
}