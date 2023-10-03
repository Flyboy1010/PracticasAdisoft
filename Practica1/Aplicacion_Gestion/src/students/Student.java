package students;

import java.util.ArrayList;

public class Student {
    private final String _name;
    private final int _nia;
    private ArrayList<Subject> _subjects;

    public Student(String name, int nia) {
        _name = name;
        _nia = nia;

        _subjects = new ArrayList<>();
    }

    public String getName() {
        return _name;
    }

    public int getNIA() {
        return _nia;
    }

    public ArrayList<Subject> getSubjects() {
        return _subjects;
    }

    public boolean addSubject(Subject subject) { // returns true if success
        boolean contains = _subjects.contains(subject);

        if (!contains) {
            _subjects.add(subject);
            subject.addStudent(this);
        }

        return !contains;
    }

    public boolean removeSubject(Subject subject) { // returns true if success
        boolean removed = _subjects.remove(subject);
        subject.removeStudent(this);

        return removed;
    }

    public void clearSubjects() {
        for (Subject subject : _subjects) {
            subject.removeStudent(this);
        }

        _subjects.clear();
    }

    public void print() {
        System.out.println("NIA: " + _nia + ", NAME: " + _name);
    }

    public void prettyPrint() {
        System.out.println("NAME: " + _name);
        System.out.println("NIA: " + _nia);
        System.out.println("SUBJECTS: ");

        for (Subject subject : _subjects) {
            System.out.println("\tID: " + subject.getID() + ", NAME: " + subject.getName());
        }
    }
}
