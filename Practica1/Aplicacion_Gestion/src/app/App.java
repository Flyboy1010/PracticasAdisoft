package app;

import app.commands.*;
import commands.Command;
import commands.CommandRegistry;
import students.Manager;
import students.Student;
import students.Subject;

import java.util.Scanner;

public class App {
    private CommandRegistry _commandRegistry = new CommandRegistry();
    private Scanner _input = new Scanner(System.in);
    private Manager _manager = new Manager();
    private boolean _running = true;

    public App() {
        // register commands

        _commandRegistry.registerCommand("help", new HelpCommand(_commandRegistry));
        _commandRegistry.registerCommand("list", new ListStudentsCommand(_manager));
        _commandRegistry.registerCommand("find", new FindStudentCommand(_manager, _input));
        _commandRegistry.registerCommand("add", new AddStudentCommand(_manager, _input));
        _commandRegistry.registerCommand("remove", new RemoveStudentCommand(_manager, _input));
        _commandRegistry.registerCommand("add_s", new AddSubjectToStudentCommand(_manager,_input));
        _commandRegistry.registerCommand("remove_s", new RemoveSubjectFromStudentCommand(_manager, _input));
        _commandRegistry.registerCommand("save", new SaveCommand(_manager));
        _commandRegistry.registerCommand("quit", new QuitCommand(this));

        // load subject & student files, (the order is important, do not change)

        _manager.readSubjectsFile("data/subjects.txt");
        _manager.readStudentsFile("data/students.txt");
    }

    public void stop() {
        _running = false;
    }

    private void promptSelectAction() {
        // get command as input

        System.out.print("Introduce command, (\"help\" for help): ");
        String commandString = _input.nextLine().toLowerCase();

        // get command

        Command command = _commandRegistry.getCommand(commandString);

        // execute command if found

        if (command != null) {
            command.execute();
        } else {
            System.out.println("Command not found");
        }
    }

    private void update() {
        while (_running) {
            promptSelectAction();
        }
    }

    public static void main(String[] args) {
        // create app.App instance

        App app = new App();

        // update the app

        app.update();
    }
}