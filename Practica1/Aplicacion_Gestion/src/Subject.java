public class Subject {
    private final String _name;
    private final int _id;

    public Subject(String name, int id) {
        _name = name;
        _id = id;
    }

    public String getName() {
        return _name;
    }

    public int getID() {
        return _id;
    }

    public static Subject parseString(String subjectString) {
        // split into subject fields

        String[] subjectFields = subjectString.split(",");

        // parse subject fields

        int subjectID = Integer.parseInt(subjectFields[0]);
        String subjectName = subjectFields[1];

        return new Subject(subjectName, subjectID);
    }
}
