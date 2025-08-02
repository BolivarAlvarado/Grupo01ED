package main;

import java.util.Scanner;

public class Jugador {

    private char caracter;

    public Jugador(char caracter) {
        this.caracter = caracter;
    }

    public char getCaracter() {
        return caracter;
    }

    public static char elegirCaracter(Scanner sc) {
        while (true) {
            System.out.print("Elige el carácter 'X' o 'O': ");
            String opc = sc.next().toUpperCase();
            if (opc.equals("X")) return 'X';
            if (opc.equals("O")) return 'O';
            System.out.println("Opción no válida. Intenta de nuevo.");
        }
    }

    public void hacerJugada(Scanner sc, Tablero tablero) {
        int fila, columna;
        while (true) {
            System.out.print("Jugador '" + caracter + "' ingresa fila (0-2): ");
            fila = sc.nextInt();
            System.out.print("Jugador '" + caracter + "' ingresa columna (0-2): ");
            columna = sc.nextInt();

            if (tablero.colocarMarca(fila, columna, caracter)) break;
            else System.out.println("Movimiento inválido. Posición ocupada o fuera de rango.");
        }
    }
}

