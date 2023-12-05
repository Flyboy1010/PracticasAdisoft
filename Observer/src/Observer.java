import java.util.ArrayList;

// Interfaz Observer
public abstract class Observer {
    protected Subject subject;

    public abstract void update();

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
