import java.util.ArrayList;

public class Student {
    private String _name;
    private int _nia;
    private ArrayList<Integer> _subjectsIDs; // TODO: care if null

    public Student(String name, int nia, ArrayList<Integer> subjectsIDs) {
        _name = name;
        _nia = nia;
        _subjectsIDs = subjectsIDs;
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

    private int findSubject(int subjectID) { // returns the index, -1 if not found
        for (int i = 0; i < _subjectsIDs.size(); i++) {
            if (_subjectsIDs.get(i) == subjectID) {
                return i;
            }
        }

        return -1;
    }

    public boolean registerSubject(int subjectID) { // returns true if success
        int index = findSubject(subjectID);

        if (index == -1) { // if not found add the subject
            _subjectsIDs.add(subjectID);
        }

        return index == -1;
    }

    public boolean deregisterSubject(int subjectID) { // returns true if success
        int index = findSubject(subjectID);

        if (index != -1) { // if found remove the subject
            _subjectsIDs.remove(index);
        }

        return index != -1;
    }

    public void printInfo() {
        System.out.println("NAME: " + _name);
        System.out.println("NIA: " + _nia);
        System.out.print("SUBJECTS: ");

        for (int subjectID : _subjectsIDs) {
            System.out.print(Integer.toString(subjectID) + '|');
        }

        System.out.println();
    }

    @Override
    public String toString() {
        StringBuilder studentString = new StringBuilder(String.format("%d,%s,", _nia, _name));

        for (int subjectID : _subjectsIDs) {
            studentString.append(Integer.toString(subjectID)).append('|');
        }

        return studentString.toString();
    }

    public static Student parseString(String studentString) {
        // split into student fields

        String[] studentFields = studentString.split(",");

        // parse student fields

        int studentNIA = Integer.parseInt(studentFields[0]);
        String studentName = studentFields[1];
        ArrayList<Integer> studentSubjectsIDs = null;

        if (studentFields.length > 2) {
            studentSubjectsIDs = new ArrayList<>();
            String[] studentSubjectsStringIDs = studentFields[2].split("-");

            for (String subjectStringID : studentSubjectsStringIDs) {
                studentSubjectsIDs.add(Integer.parseInt(subjectStringID));
            }
        }

        // create new student

        return new Student(studentName, studentNIA, studentSubjectsIDs);
    }
}
