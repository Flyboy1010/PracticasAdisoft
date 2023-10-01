package students;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

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

    public boolean studentAddSubject(int studentNIA, int subjectID) {
        Student student = findStudent(studentNIA);
        Subject subject = findSubject(subjectID);

        if (student == null || subject == null) {
            return false;
        }

        return student.addSubject(subjectID);
    }

    public boolean studentRemoveSubject(int studentNIA, int subjectID) {
        Student student = findStudent(studentNIA);
        Subject subject = findSubject(subjectID);

        if (student == null || subject == null) {
            return false;
        }

        return student.removeSubject(subjectID);
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

        String[] fields = studentString.split(",");

        // parse student fields

        int nia = Integer.parseInt(fields[0]);
        String name = fields[1];
        ArrayList<Integer> subjectsIDs = null;

        if (fields.length > 2) {
            subjectsIDs = new ArrayList<>();
            String[] subjectsIDsString = fields[2].split("-");

            for (String subjectIDString : subjectsIDsString) {
                subjectsIDs.add(Integer.parseInt(subjectIDString));
            }
        }

        // create new student

        return new Student(name, nia, subjectsIDs);
    }

    private static Subject stringToSubject(String subjectString) {
        // split into subject fields

        String[] fields = subjectString.split(",");

        // parse subject fields

        int id = Integer.parseInt(fields[0]);
        String name = fields[1];
        ArrayList<Integer> studentsIDs = null;

        if (fields.length > 2) {
            studentsIDs = new ArrayList<>();
            String[] studentsIDsString = fields[2].split("-");

            for (String studentIDString : studentsIDsString) {
                studentsIDs.add(Integer.parseInt(studentIDString));
            }
        }

        return new Subject(name, id, studentsIDs);
    }

    /* Me gustaria declarle mi amor pero solo puedo declarar variables :( */

    private static String studentToString(Student student) {
        StringBuilder studentString = new StringBuilder();

        // nia and name

        studentString.append(student.getName())
                .append(',')
                .append(student.getName())
                .append(',');

        // subjects ids

        for (int subjectID : student.getSubjectsIDs()) {
            studentString.append(subjectID)
                    .append('-');
        }

        // remove last '-'

        studentString.deleteCharAt(studentString.length() - 1);

        return studentString.toString();
    }

    private static String subjectToString(Subject subject) {
        return null;
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

                writer.write(studentToString(student)+ '\n');
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


            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
