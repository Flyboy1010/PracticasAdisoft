public class Timer extends Subject {
    public int h = 0;
    public int m = 0;
    public int s = 0;

    public int getH() { return h; }
    public int getM() { return m; }
    public int getS() { return s; }

    public void Tick() {
        s++;

        notifyObservers();
    }
}