package students;

import java.util.ArrayList;

public class Subject {
    private final String _name;
    private final int _id;
    private ArrayList<Student> _students;

    public Subject(String name, int id) {
        _name = name;
        _id = id;
        _students = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public int getID() {
        return _id;
    }

    public ArrayList<Student> getStudents() {
        return _students;
    }

    public void addStudent(Student student) {
        boolean alreadyAdded = _students.contains(student);

        if (!alreadyAdded) {
            _students.add(student);
            student.addSubject(this);
        }
    }

    public void removeStudent(Student student) {
        boolean removed = _students.remove(student);

        if (removed) {
            student.removeSubject(this);
        }
    }

    public void clearStudents() {
        ArrayList<Student> students = new ArrayList<>(_students);

        for (Student student : students) {
            student.removeSubject(this);
        }

        _students.clear();
    }

    public void print() {
        System.out.println("ID: " + _id + ", NAME: " + _name);
    }

    public void prettyPrint() {
        System.out.println("NAME: " + _name);
        System.out.println("ID: " + _id);
        System.out.println("STUDENTS: ");

        for (Student student : _students) {
            System.out.print('\t');
            student.print();
        }
    }
}
