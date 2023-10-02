package students;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private static final char SEPARATOR_FIELD = ',';
    private static final char SEPARATOR_NIA = '-';
    private static final char SEPARATOR_ID = '-';

    private HashMap<Integer, Subject> _subjects = new HashMap<>();
    private HashMap<Integer, Student> _students = new HashMap<>();

    // subject

    public Subject findSubject(int subjectID) {
        if (_subjects.containsKey(subjectID)) {
            return _subjects.get(subjectID);
        }

        return null;
    }

    // add, find, remove student

    public void addStudent(Student student) { // adds student
        if (student != null) {
            _students.put(student.getNIA(), student);
        }
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

    public void studentAddSubject(int studentNIA, int subjectID) {
        Student student = findStudent(studentNIA);
        Subject subject = findSubject(subjectID);

        if (student != null) student.addSubject(subjectID);
        if (subject != null) subject.addStudent(studentNIA);
    }

    public void studentRemoveSubject(int studentNIA, int subjectID) {
        Student student = findStudent(studentNIA);
        Subject subject = findSubject(subjectID);

        if (student != null) student.removeSubject(subjectID);
        if (subject != null) subject.removeStudent(studentNIA);
    }

    // print student info

    public void printStudentInfo(int studentNIA) {
        Student student = findStudent(studentNIA);

        if (student != null) {
            System.out.println("NAME: " + student.getName());
            System.out.println("NIA: " + student.getNIA());
            System.out.println("SUBJECTS: ");

            for (int subjectID : student.getSubjectsIDs()) {
                Subject subject = findSubject(subjectID);

                if (subject != null) {
                    System.out.println("\tID: " + subject.getID() + ", NAME: " + subject.getName());
                }
            }
        }
    }

    public void printAllStudentsBasicInfo() {
        for (HashMap.Entry<Integer, Student> entry : _students.entrySet()) {
            // get student

            Student student = entry.getValue();

            // print just nia and name

            System.out.println("NIA: " + student.getNIA() + ", NAME: " + student.getName());
        }
    }

    // parse student

    private static Student stringToStudent(String studentString) {
        // split into student fields

        String[] fields = studentString.split(String.valueOf(SEPARATOR_FIELD));

        // parse student fields

        int nia = Integer.parseInt(fields[0]);
        String name = fields[1];
        ArrayList<Integer> subjectsIDs = null;

        if (fields.length > 2) {
            subjectsIDs = new ArrayList<>();
            String[] subjectsIDsString = fields[2].split(String.valueOf(SEPARATOR_ID));

            for (String subjectIDString : subjectsIDsString) {
                subjectsIDs.add(Integer.parseInt(subjectIDString));
            }
        }

        // create new student

        return new Student(name, nia, subjectsIDs);
    }

    private static Subject stringToSubject(String subjectString) {
        // split into subject fields

        String[] fields = subjectString.split(String.valueOf(SEPARATOR_FIELD));

        // parse subject fields

        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        ArrayList<Integer> studentsNIAs = null;

        if (fields.length > 2) {
            studentsNIAs = new ArrayList<>();
            String[] studentsNIAsString = fields[2].split(String.valueOf(SEPARATOR_NIA));

            for (String studentNIAString : studentsNIAsString) {
                studentsNIAs.add(Integer.parseInt(studentNIAString));
            }
        }

        return new Subject(name, id, studentsNIAs);
    }

    /* Me gustaria declarle mi amor pero solo puedo declarar variables :( */

    private static String studentToString(Student student) {
        StringBuilder studentString = new StringBuilder();

        // nia and name

        studentString.append(student.getNIA())
                .append(SEPARATOR_FIELD)
                .append(student.getName());

        // subjects ids

        ArrayList<Integer> subjectsIDs = student.getSubjectsIDs();

        if (!subjectsIDs.isEmpty()) {
            studentString.append(SEPARATOR_FIELD);

            for (int subjectID : student.getSubjectsIDs()) {
                studentString.append(subjectID)
                        .append(SEPARATOR_ID);
            }

            // remove last separator

            studentString.deleteCharAt(studentString.length() - 1);
        }

        return studentString.toString();
    }

    private static String subjectToString(Subject subject) {
        StringBuilder subjectString = new StringBuilder();

        // id and name

        subjectString.append(subject.getID())
                .append(SEPARATOR_FIELD)
                .append(subject.getName());

        // student nias

        ArrayList<Integer> studentsNIAs = subject.getStudentsNIAs();

        if (!studentsNIAs.isEmpty()) {
            subjectString.append(SEPARATOR_FIELD);

            for (int studentNIA : subject.getStudentsNIAs()) {
                subjectString.append(studentNIA)
                        .append(SEPARATOR_NIA);
            }

            // remove last separator

            subjectString.deleteCharAt(subjectString.length() - 1);
        }

        return subjectString.toString();
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

                    Subject subject = stringToSubject(line);
                    _subjects.put(subject.getID(), subject);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
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

                    Student student = stringToStudent(line);
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

                writer.write(studentToString(student));
                writer.newLine();
            }

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
