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

    protected void addStudent(int studentNIA) {
        int index = _studentsNIAs.indexOf(studentNIA);

        if (index == -1) {
            _studentsNIAs.add(studentNIA);
        }
    }

    protected void removeStudent(int studentNIA) {
        _studentsNIAs.remove(Integer.valueOf(studentNIA));
    }
}
