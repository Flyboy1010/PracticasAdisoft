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

    private static Student parseStudentString(String studentString) {
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

    private static Subject parseSubjectString(String subjectString) {
        // split into subject fields

        String[] subjectFields = subjectString.split(",");

        // parse subject fields

        int subjectID = Integer.parseInt(subjectFields[0]);
        String subjectName = subjectFields[1];

        return new Subject(subjectName, subjectID);
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

                    Subject subject = parseSubjectString(line);
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

                    Student student = parseStudentString(line);
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

                writer.write(student.toString() + '\n');
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
