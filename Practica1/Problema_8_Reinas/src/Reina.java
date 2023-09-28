public class Reina extends Pieza {

    public Reina(int x, int y, Pieza vecina) {
        super(x, y, vecina);
        _tipo = Tipo.REINA;
    }

    @Override
    protected boolean puedeAtacar(int x, int y) {
        // comprobar la misma fila

        if (_y == y) return true;

        // comprobamos la diagonal

        int dx = Math.abs(x - _x);
        int dy = Math.abs(y - _y);

        if (dx == dy) return true;

        // comprobamos la vecina si tiene vecina

        if (_vecina != null) {
            return _vecina.puedeAtacar(x, y);
        }

        // happens

        return false;
    }
}
