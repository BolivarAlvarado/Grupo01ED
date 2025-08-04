package main.Modelo;

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
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                tablero[i][j] = ' ';
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
}

