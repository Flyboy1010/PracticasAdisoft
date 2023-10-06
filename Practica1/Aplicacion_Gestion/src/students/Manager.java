package students;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private static final char SEPARATOR_FIELD = ',';
    private static final char SEPARATOR_ID = '-';

    private HashMap<Integer, Subject> _subjects = new HashMap<>();
    private HashMap<Integer, Student> _students = new HashMap<>();

    // find, add, remove subject

    public Subject findSubject(int subjectID) {
        if (_subjects.containsKey(subjectID)) {
            return _subjects.get(subjectID);
        }

        return null;
    }

    public void addSubject(Subject subject) {
        _subjects.put(subject.getID(), subject);
    }

    public void removeSubject(Subject subject) {
        subject.clearStudents();
        _subjects.remove(subject.getID());
    }

    // find, add, remove student

    public Student findStudent(int studentNIA) { // returns student, null if not found
        if (_students.containsKey(studentNIA)) {
            return _students.get(studentNIA);
        }

        return null;
    }

    public void addStudent(Student student) { // adds student
        _students.put(student.getNIA(), student);
    }

    public void removeStudent(Student student) {
        student.clearSubjects();
        _students.remove(student.getNIA());
    }

    // print all students

    public void printStudents() {
        for (HashMap.Entry<Integer, Student> entry : _students.entrySet()) {
            // get student

            Student student = entry.getValue();

            // print just nia and name

            student.print();
        }
    }

    // print subjects

    public void printSubjects() {
        for (HashMap.Entry<Integer, Subject> entry : _subjects.entrySet()) {
            // get subject

            Subject subject = entry.getValue();

            // print just id and name

            subject.print();
        }
    }

    // parse subject

    private Subject stringToSubject(String subjectString) {
        // split into subject fields

        String[] fields = subjectString.split(String.valueOf(SEPARATOR_FIELD));

        // parse basic subject fields

        int id = Integer.parseInt(fields[0]);
        String name = fields[1];

        // create subject

        return new Subject(name, id);
    }

    // serialize subject

    private String subjectToString(Subject subject) {
        // id and name

        return String.valueOf(subject.getID()) + SEPARATOR_FIELD + subject.getName();
    }

    // parse student

    private Student stringToStudent(String studentString) {
        // split into student fields

        String[] fields = studentString.split(String.valueOf(SEPARATOR_FIELD));

        // parse basic student fields

        int nia = Integer.parseInt(fields[0]);
        String name = fields[1];

        // create student

        Student student = new Student(name, nia);

        // parse subjects

        if (fields.length > 2) {
            String[] subjectsIDsString = fields[2].split(String.valueOf(SEPARATOR_ID));

            for (String subjectIDString : subjectsIDsString) {
                int subjectID = Integer.parseInt(subjectIDString);
                Subject subject = findSubject(subjectID);

                if (subject != null) {
                    student.addSubject(subject);
                }
            }
        }

        //

        return student;
    }

    // serialize student

    private String studentToString(Student student) {
        StringBuilder studentString = new StringBuilder();

        // nia and name

        studentString.append(student.getNIA())
                .append(SEPARATOR_FIELD)
                .append(student.getName());

        // subjects ids

        ArrayList<Subject> subjects = student.getSubjects();

        if (!subjects.isEmpty()) {
            studentString.append(SEPARATOR_FIELD);

            for (Subject subject : subjects) {
                studentString.append(subject.getID())
                        .append(SEPARATOR_ID);
            }

            // remove last separator

            studentString.deleteCharAt(studentString.length() - 1);
        }

        return studentString.toString();
    }

    // read subjects file (this has to be loaded before loading the students file)

    public void readSubjectsFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // read each line

            String line;

            while ((line = reader.readLine()) != null) {
                // check that the line is not empty

                if (!line.isEmpty()) {
                    // parse the student and put it in a hashmap

                    Subject subject = stringToSubject(line);
                    _subjects.put(subject.getID(), subject);
                }
            }

            // close

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // read students file (load after loading the subjects file)

    public void readStudentsFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // read each line

            String line;

            while ((line = reader.readLine()) != null) {
                // check that the line is not empty

                if (!line.isEmpty()) {
                    // parse the student and put it in a hashmap

                    Student student = stringToStudent(line);
                    _students.put(student.getNIA(), student);
                }
            }

            // close

            reader.close();

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

                writer.write(studentToString(student));
                writer.newLine();
            }

            // close

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // save subjects file

    public void saveSubjectsFile(String filePath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

            // write students

            for (HashMap.Entry<Integer, Subject> entry : _subjects.entrySet()) {
                Subject subject = entry.getValue();

                writer.write(subjectToString(subject));
                writer.newLine();
            }

            // close

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
