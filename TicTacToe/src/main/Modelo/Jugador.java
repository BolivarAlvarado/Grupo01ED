package main.Modelo;

import java.util.Scanner;

public class Jugador {

    private char caracter;

    //Constructor
    public Jugador(char caracter) {
        this.caracter = caracter;
    }

    //Getter
    public char getCaracter() {
        return caracter;
    }

    //Mediante un while siempre verdadero que hasta que no escoja o X o O, avisara que es invalido y pedira que se lo ingrese de nuevo
    public static char elegirCaracter(Scanner sc) {
        while (true) {
            System.out.print("Elige tu caracter 'X' o 'O': ");
            String opc = sc.next().toUpperCase();
            if (opc.equals("X")) return 'X';
            if (opc.equals("O")) return 'O';
            System.out.println("Opción no disponible. Intenta de nuevo.");
        }
    }

    //Mediante un while, se asigna una fila y columna, verificando con el metodo colocarMarca si cumple el rango y esta libre el espacio
    public void hacerJugada(Scanner sc, Tablero tablero) {
        int fila, columna;
        while (true) {
            System.out.print("Jugador '" + caracter + "' ingresa fila (0-2): ");
            fila = sc.nextInt();
            System.out.print("Jugador '" + caracter + "' ingresa columna (0-2): ");
            columna = sc.nextInt();

            if (tablero.colocarMarca(fila, columna, caracter)) break; //break al while cuando se verifica correctamente los valores ingresados
            else System.out.println("Movimiento invalido. Posicion ocupada o fuera de rango.");
        }
    }
}

