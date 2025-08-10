package main.modelo;

public class Tablero {

    //Declaramos una variable estatica y final porque el tablero sera de 3x3
    public static final int SIZE = 3;
    private char[][] tablero;

    //inicializamos el tablero con el tamano 3x3 y se llama a inicializarTablero para llenarlo de espacios
    public Tablero() {
        tablero = new char[SIZE][SIZE];
        inicializarTablero();
    }

    //getter
    public char[][] getTablero() {
        return tablero;
    }

    //Recorre los 3x3 espacios y los deja en blanco para llenarlos de las respuestas del usuario
    private void inicializarTablero() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    //Metodo para mostrar por consola un diseño para el tablero y que muestra el contenido de cada celda
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

    //Metodo que se utilizara en otras clases para verificar que la posicion ingresada por el usuario es valida y no esta ocupada para cambiarla por la ingresada
    public boolean colocarMarca(int fila, int columna, char marca) {
        if (fila >= 0 && fila < SIZE && columna >= 0 && columna < SIZE && tablero[fila][columna] == ' ') {
            tablero[fila][columna] = marca;
            return true;
        }
        return false;
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

    public boolean revisarTablero(String[][] tablero) {
        // Revisar filas
        for (int i = 0; i < SIZE; i++) {
            if (tablero[i][0] != null && !tablero[i][0].isEmpty()
                    && tablero[i][0].equals(tablero[i][1])
                    && tablero[i][1].equals(tablero[i][2])) {
                return true;
            }
        }

        // Revisar columnas
        for (int j = 0; j < SIZE; j++) {
            if (tablero[0][j] != null && !tablero[0][j].isEmpty()
                    && tablero[0][j].equals(tablero[1][j])
                    && tablero[1][j].equals(tablero[2][j])) {
                return true;
            }
        }

        // Revisar diagonales
        if (tablero[0][0] != null && !tablero[0][0].isEmpty()
                && tablero[0][0].equals(tablero[1][1])
                && tablero[1][1].equals(tablero[2][2])) {
            return true;
        }

        return tablero[0][2] != null && !tablero[0][2].isEmpty()
                && tablero[0][2].equals(tablero[1][1])
                && tablero[1][1].equals(tablero[2][0]);
    }

    public void reiniciar() {
        inicializarTablero();
    }
}
