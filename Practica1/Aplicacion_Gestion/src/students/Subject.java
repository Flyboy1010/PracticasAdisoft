package students;

import java.util.ArrayList;
import java.util.Objects;

public class Subject {
    private final String _name;
    private final int _id;
    private ArrayList<Integer> _studentsNIAs;

    public Subject(String name, int id, ArrayList<Integer> studentsNIAs) {
        _name = name;
        _id = id;
        _studentsNIAs = Objects.requireNonNullElseGet(studentsNIAs, ArrayList::new);
    }

    public String getName() {
        return _name;
    }

    public int getID() {
        return _id;
    }

    public ArrayList<Integer> getStudentsNIAs() {
        return _studentsNIAs;
    }

    public void addStudent(int studentNIA) {

    }

    public void removeStudent(int studentNIA) {

    }
}
