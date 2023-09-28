// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // create manager

        Manager manager = new Manager();

        // load students and subjects files

        manager.readStudentsFile("students.csv");
        manager.readSubjectsFile("subjects.csv");

        //

        Student student = new Student("David Gispert Gutiérrez", 776900, List.of(123124, 12312313));
    }
}