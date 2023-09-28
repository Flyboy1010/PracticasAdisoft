import java.io.*;
import java.util.HashMap;

public class Manager {

    private HashMap<Integer, Subject> _subjects = new HashMap<>();
    private HashMap<Integer, Student> _students = new HashMap<>();

    // add, find, remove student

    public void addStudent(Student student) { // adds student
        _students.put(student.getNIA(), student);
    }

    public Student findStudent(int studentNIA) { // returns student, null if not found
        if (_students.containsKey(studentNIA)) {
            return _students.get(studentNIA);
        }

        return null;
    }

    public boolean removeStudent(int studentNIA) { // returns true if successfully removed
        Student student = findStudent(studentNIA);

        if (student != null) {
            _students.remove(studentNIA);
            return true;
        }

        return false;
    }

    public boolean registerSubjectToStudent(int subjectID, int studentNIA) {
        Student student = findStudent(studentNIA);

        if (student != null) {
            return student.registerSubject(subjectID);
        }

        return false;
    }

    public boolean deregisterSubjectToStudent(int subjectID, int studentNIA) {
        Student student = findStudent(studentNIA);

        if (student != null) {
            return student.deregisterSubject(subjectID);
        }

        return false;
    }

    // read subjects file

    public void readSubjectsFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // read each line

            String line;

            while ((line = reader.readLine()) != null) {
                // check that the line is not empty

                if (!line.isEmpty()) {
                    // parse the student and put it in a hashmap

                    Subject subject = Subject.parseString(line);
                    _subjects.put(subject.getID(), subject);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // print student info

    public void printStudentInfo(int studentNIA) {
        Student student = findStudent(studentNIA);

        if (student != null) {
            student.printInfo();
        }
    }

    // read students file

    public void readStudentsFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // read each line

            String line;

            while ((line = reader.readLine()) != null) {
                // check that the line is not empty

                if (!line.isEmpty()) {
                    // parse the student and put it in a hashmap

                    Student student = Student.parseString(line);
                    _students.put(student.getNIA(), student);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // save students file

    public void saveStudentsFile(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // write students

            for (HashMap.Entry<Integer, Student> entry : _students.entrySet()) {
                Student student = entry.getValue();
                String studentString = student.toString();

                writer.write(studentString + '\n');
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
