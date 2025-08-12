package main.modelo;

public class Tablero {

    public static final int SIZE = 3;
    private char[][] tablero;

    public Tablero() {
        tablero = new char[SIZE][SIZE];
        inicializarTablero();
    }

    public char[][] getTablero() {
        return tablero;
    }

    private void inicializarTablero() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tablero[i][j] = ' ';
            }
        }
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

    // Coloca marca sin validar (usado por minimax para probar movimientos)
    public void colocarMarcaForzada(int fila, int columna, char marca) {
        tablero[fila][columna] = marca;
    }

    public boolean estaLleno() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] revisarTablero() {
        // Revisar filas
        for (int i = 0; i < SIZE; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return new int[][]{{i, 0}, {i, 1}, {i, 2}};
            }
        }

        // Revisar columnas
        for (int j = 0; j < SIZE; j++) {
            if (tablero[0][j] != ' ' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return new int[][]{{0, j}, {1, j}, {2, j}};
            }
        }

        // Revisar diagonales
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return new int[][]{{0, 0}, {1, 1}, {2, 2}};
        }

        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return new int[][]{{0, 2}, {1, 1}, {2, 0}};
        }

        return null;
    }

    public void reiniciar() {
        inicializarTablero();
    }

    // --- NUEVOS MÉTODOS PARA MINIMAX ---
    public char getCasilla(int fila, int columna) {
        return tablero[fila][columna];
    }

    public boolean haGanado(char marca) {
        // Revisar filas y columnas
        for (int i = 0; i < SIZE; i++) {
            if (tablero[i][0] == marca && tablero[i][1] == marca && tablero[i][2] == marca) return true;
            if (tablero[0][i] == marca && tablero[1][i] == marca && tablero[2][i] == marca) return true;
        }
        // Revisar diagonales
        if (tablero[0][0] == marca && tablero[1][1] == marca && tablero[2][2] == marca) return true;
        if (tablero[0][2] == marca && tablero[1][1] == marca && tablero[2][0] == marca) return true;

        return false;
    }
}

