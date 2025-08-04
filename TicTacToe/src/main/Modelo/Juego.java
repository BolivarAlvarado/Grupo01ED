package main.Modelo;

import java.util.Scanner;

public class Juego {

    //Metodo principal que inicializa el juego
    public void comenzarJuego() {
        Scanner sc = new Scanner(System.in);
        Tablero tablero = new Tablero();

        // Elegir caracteres
        System.out.println("Jugador 1:");
        char caracter1 = Jugador.elegirCaracter(sc);
        char caracter2 = (caracter1 == 'X') ? 'O' : 'X';

        Jugador jugador1 = new Jugador(caracter1);
        Jugador jugador2 = new Jugador(caracter2);

        //Asignamos el jugador actual, en este caso el jugador1
        Jugador actual = jugador1;
        int turnos = 0;
        // Bucle principal del juego
        while (true) {
            //Inicializa el tablero
            tablero.mostrarTablero();
            actual.hacerJugada(sc, tablero);
            turnos++;

            //Se verifica si un jugador ya gano y si es asi, se acaba el juego
            if (verificarGanador(tablero.getTablero(), actual.getCaracter())) {
                tablero.mostrarTablero();
                System.out.println("Jugador '" + actual.getCaracter() + "' gana!");
                break;
            }

            //Si se llenan todos los espacios del tablero y nadie gano, se declara un empate y acaba el juego
            if (turnos == 9) {
                tablero.mostrarTablero();
                System.out.println("Empate!");
                break;
            }

            // Cambiar de jugador
            actual = (actual == jugador1) ? jugador2 : jugador1;
        }
        sc.close();
    }

    //Metodo que verificara que se haga una linea horizontal, vertical o diagonal del caracter.
    public boolean verificarGanador(char[][] tablero, char jugador) {
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador) return true;
            if (tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador) return true;
        }

        // Verificar diagonales
        if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador) return true;
        if (tablero[0][2] == jugador && tablero[1][1] == jugador && tablero[2][0] == jugador) return true;

        return false;
    }
}
