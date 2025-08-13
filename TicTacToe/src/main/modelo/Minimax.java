package main.modelo;

import java.util.ArrayList;
import java.util.List;

import estructuras.arbol.Nodo;

public class Minimax {

    private char pc;      // Símbolo de la PC
    private char jugador; // Símbolo del jugador humano

    public Minimax(char pc, char jugador) {
        this.pc = pc;
        this.jugador = jugador;
    }

    // Nodo que representa un estado del juego en el árbol
    // Construye el árbol de estados recursivamente
    private void construirArbol(Nodo nodo, boolean esMaximizador) {
        if (nodo.estado.revisarTablero() != null || nodo.estado.estaLleno()) {
            return; // Nodo hoja
        }

        char simboloActual = esMaximizador ? pc : jugador;

        for (int i = 0; i < Tablero.SIZE; i++) {
            for (int j = 0; j < Tablero.SIZE; j++) {
                if (nodo.estado.getTablero()[i][j] == ' ') {
                    Tablero nuevoEstado = nodo.estado.clonar();
                    nuevoEstado.colocarMarca(i, j, simboloActual);

                    Nodo hijo = new Nodo(nuevoEstado, i, j);
                    nodo.agregarHijo(hijo);

                    construirArbol(hijo, !esMaximizador);
                }
            }
        }
    }

    // Aplica minimax con poda alfa-beta sobre el árbol
    private int minimax(Nodo nodo, int profundidad, int alfa, int beta, boolean esMaximizador) {
        int[][] ganador = nodo.estado.revisarTablero();
        if (ganador != null) {
            char simboloGanador = nodo.estado.getTablero()[ganador[0][0]][ganador[0][1]];
            if (simboloGanador == pc) return 10 - profundidad;
            if (simboloGanador == jugador) return profundidad - 10;
        }

        if (nodo.estado.estaLleno()) {
            return 0; // Empate
        }

        if (esMaximizador) {
            int mejorValor = Integer.MIN_VALUE;
            for (Nodo hijo : nodo.hijos) {
                int valor = minimax(hijo, profundidad + 1, alfa, beta, false);
                mejorValor = Math.max(mejorValor, valor);
                alfa = Math.max(alfa, valor);
                if (beta <= alfa) break;
            }
            nodo.valorMinimax = mejorValor;
            return mejorValor;
        } else {
            int mejorValor = Integer.MAX_VALUE;
            for (Nodo hijo : nodo.hijos) {
                int valor = minimax(hijo, profundidad + 1, alfa, beta, true);
                mejorValor = Math.min(mejorValor, valor);
                beta = Math.min(beta, valor);
                if (beta <= alfa) break;
            }
            nodo.valorMinimax = mejorValor;
            return mejorValor;
        }
    }

    // Devuelve el mejor movimiento para la PC
    public int[] mejorMovimiento(Tablero tablero) {
        Nodo raiz = new Nodo(tablero.clonar(), -1, -1);
        construirArbol(raiz, true);

        int mejorValor = Integer.MIN_VALUE;
        int[] mejorMovimiento = {-1, -1};

        for (Nodo hijo : raiz.hijos) {
            int valor = minimax(hijo, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
            if (valor > mejorValor) {
                mejorValor = valor;
                mejorMovimiento[0] = hijo.filaMovimiento;
                mejorMovimiento[1] = hijo.colMovimiento;
            }
        }
        return mejorMovimiento;
    }
}


