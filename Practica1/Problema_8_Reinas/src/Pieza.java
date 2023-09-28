public abstract class Pieza {

    public enum Tipo {
        NINGUNA,
        REINA,
        TORRE
    }

    protected final int _x;
    protected int _y;
    protected final Pieza _vecina;
    protected Tipo _tipo = Tipo.NINGUNA;

    public static boolean PRIMERA_NO_PUEDE_AVANZAR = false;

    public Pieza(int x, int y, Pieza vecina) {
        _x = x;
        _y = y;
        _vecina = vecina;
    }

    public int getX() { return _x; }
    public int getY() { return _y; }

    public Tipo getTipo() { return _tipo; }

    public void primera() {
        // comprobar si tengo vecina

        if (_vecina != null) {

            // primero colocamos la vecina

            _vecina.primera();

            boolean meAtacan = _vecina.puedeAtacar(_x, _y);

            // si me atacan

            if (meAtacan) {
                avanza();
            }
        }
    }

    protected abstract boolean puedeAtacar(int x, int y);

    public void avanza() {
        if (_y < 8) {
            // puedo avanzar
            _y++;

            if (_vecina != null) {
                boolean meAtacan = _vecina.puedeAtacar(_x, _y);

                // si me atacan

                if (meAtacan) {
                    avanza();
                }
            }

        } else {
            if (_vecina != null) {
                // si no, vuelvo a la fila inicial y avanzo la vecina
                _y = 1;

                _vecina.avanza();
                primera();
            } else {
                // si no tengo vecinas ya ta
                PRIMERA_NO_PUEDE_AVANZAR = true;
            }
        }
    }
}
