public class Main {
    public static void main(String[] args) {
        // Crear instancias de observadores
        DigitalClock digitalClock = new DigitalClock();

        // Crear instancia de objeto observable
        Timer timer = new Timer();

        // Agregar observadores al objeto observable
        timer.addObserver(digitalClock);

        while(true) {
            timer.Tick();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}