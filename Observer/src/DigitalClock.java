// Clase concreta que implementa la interfaz Observer
class DigitalClock extends Observer {

    @Override
    public void update() {
        Timer timer = (Timer)subject;

        System.out.println("Digital clock: " + timer.getH() + ":" + timer.getM() + ":" + timer.getS());
    }
}
