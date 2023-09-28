public class Main {

    public static void imprimirTablero(Pieza[] piezas) {
        int longitudTablero = 8; // Tamaño del tablero de ajedrez (8x8)

        char[][] tablero = new char[longitudTablero][longitudTablero];

        // Inicializar el tablero con espacios en blanco
        for (int i = 0; i < longitudTablero; i++) {
            for (int j = 0; j < longitudTablero; j++) {
                tablero[i][j] = ' ';
            }
        }

        // Colocar las reinas en el tablero
        for (Pieza pieza : piezas) {
            char caracterPieza = ' ';

            switch (pieza.getTipo()) {
                case REINA -> caracterPieza = 'Q';
                case TORRE -> caracterPieza = 'T';
            }

            tablero[7 - (pieza.getY() - 1)][pieza.getX() - 1] = caracterPieza;
        }

        // Imprimir el tablero
        System.out.println(" =================================");
        for (int i = 0; i < longitudTablero; i++) {
            for (int j = 0; j < longitudTablero; j++) {
                System.out.print(" | " + tablero[i][j]);
            }
            System.out.println(" |");
            System.out.println(" =================================");
        }
    }

    public static void imprimeSolucion(Pieza[] piezas, int numeroSolucion) {
        System.out.printf("Solucion %02d: ", numeroSolucion);

        for (Pieza pieza : piezas) {
            System.out.print(pieza.getY() + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // array de piezas

        Pieza[] piezas = new Pieza[8];

        piezas[0] = new Reina(1, 1, null);

        for (int i = 1; i < 8; i++) {
            piezas[i] = new Reina(i + 1, 1, piezas[i - 1]);
        }

        // calcular primera solucion

        int numeroSoluciones = 0;

        piezas[7].primera();
        System.out.println("Primera solucion");
        imprimirTablero(piezas);
        System.out.println();

        // calcular resto de soluciones

        while (!Pieza.PRIMERA_NO_PUEDE_AVANZAR) {
            numeroSoluciones++;
            imprimeSolucion(piezas, numeroSoluciones);
            piezas[7].avanza();
        }

        System.out.println("\nNumero de soluciones: " + numeroSoluciones);
    }
}