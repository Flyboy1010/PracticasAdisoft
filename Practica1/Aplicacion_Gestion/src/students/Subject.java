package students;

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
}
