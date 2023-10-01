// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import students.Manager;
import students.Student;

import java.util.Scanner;

public class App {

    private Scanner _input = new Scanner(System.in);
    private Manager _manager = new Manager();
    private boolean _running = true;

    public App() {
        // load student and subject files

        _manager.readStudentsFile("data/students.txt");
        _manager.readSubjectsFile("data/subjects.txt");
    }

    private void printHelp() {
        System.out.println("Command list:");
        System.out.println("\t'l' lists all students");
        System.out.println("\t'f' find student by NIA");
        System.out.println("\t'a' adds student");
        System.out.println("\t'r' removes student");
    }

    private void promptFindStudent() {
        System.out.print("Introduce student NIA: ");

        int nia = Integer.parseInt(_input.nextLine());

        _manager.printStudentInfo(nia);
    }

    private void promptAddStudent() {
        System.out.print("Introduce new student name: ");
        String name = _input.nextLine();
        System.out.print("Introduce new student NIA: ");
        int nia = Integer.parseInt(_input.nextLine());

        _manager.addStudent(new Student(name, nia, null));
    }

    private void promptRemoveStudent() {
        System.out.print("Introduce student NIA: ");
        int nia = Integer.parseInt(_input.nextLine());

        _manager.removeStudent(nia);
    }

    private void promptSelectAction() {
        System.out.print("Introduce command, 'h' for help: ");

        char command = Character.toLowerCase(_input.nextLine().charAt(0));

        switch (command) {
            case 'h':
                printHelp();
                break;
            case 'l':
                _manager.printAllStudentsBasicInfo();
                break;
            case 'f':
                promptFindStudent();
                break;
            case 'a':
                promptAddStudent();
                break;
            case 'r':
                promptRemoveStudent();
                break;
            case 's':
                _manager.saveStudentsFile("data/students.txt");
                break;
            case 'q':
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