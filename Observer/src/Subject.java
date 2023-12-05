import java.util.ArrayList;

// Interfaz Subject en lugar de Observable
public class Subject {
    protected ArrayList<Observer> observers = new ArrayList<>();

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void addObserver(Observer observer) {
        observer.setSubject(this);
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
}
