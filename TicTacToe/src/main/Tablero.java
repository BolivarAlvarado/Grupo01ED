package main;

public class Tablero {
    public static final int SIZE = 3;
    private char[][] tablero;

    public Tablero() {
        tablero = new char[SIZE][SIZE];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                tablero[i][j] = ' ';
    }

    public char[][] getTablero() {
        return tablero;
    }

    public void mostrarTablero() {
        System.out.println("-------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(tablero[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean colocarMarca(int fila, int columna, char marca) {
        if (fila >= 0 && fila < SIZE && columna >= 0 && columna < SIZE && tablero[fila][columna] == ' ') {
            tablero[fila][columna] = marca;
            return true;
        }
        return false;
    }
}

