public class Torre extends Pieza {

    public Torre(int x, int y, Pieza vecina) {
        super(x, y, vecina);
        _tipo = Tipo.TORRE;
    }

    @Override
    protected boolean puedeAtacar(int x, int y) {
        // comprobar la misma fila

        if (_y == y) return true;

        // comprobamos la vecina si tiene vecina

        if (_vecina != null) {
            return _vecina.puedeAtacar(x, y);
        }

        // happens

        return false;
    }
}
