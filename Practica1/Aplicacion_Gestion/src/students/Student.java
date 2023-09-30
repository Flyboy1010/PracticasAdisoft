package students;

import java.util.ArrayList;
import java.util.Objects;

public class Student {
    private String _name;
    private int _nia;
    private ArrayList<Integer> _subjectsIDs;

    public Student(String name, int nia, ArrayList<Integer> subjectsIDs) {
        _name = name;
        _nia = nia;

        _subjectsIDs = Objects.requireNonNullElseGet(subjectsIDs, ArrayList::new);
    }

    public String getName() {
        return _name;
    }

    public int getNIA() {
        return _nia;
    }

    public ArrayList<Integer> getSubjectsIDs() {
        return _subjectsIDs;
    }

    protected boolean addSubject(int subjectID) { // returns true if success
        int index = _subjectsIDs.indexOf(subjectID);

        // check if is not in the list

        if (index == -1) {
            _subjectsIDs.add(subjectID);
        }

        return index == -1;
    }

    protected boolean removeSubject(int subjectID) { // returns true if success
        return _subjectsIDs.remove(Integer.valueOf(subjectID));
    }
}
