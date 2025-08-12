package main.modelo;

public class Minimax {

    private char pc;      // Símbolo de la PC
    private char jugador; // Símbolo del jugador humano

    public Minimax(char pc, char jugador) {
        this.pc = pc;
        this.jugador = jugador;
    }

    public int[] mejorMovimiento(Tablero tablero) {
        int mejorValor = Integer.MIN_VALUE;
        int[] mejorMovimiento = {-1, -1};

        for (int i = 0; i < Tablero.SIZE; i++) {
            for (int j = 0; j < Tablero.SIZE; j++) {
                if (tablero.getTablero()[i][j] == ' ') {
                    tablero.colocarMarca(i, j, pc);
                    int valorMovimiento = minimax(tablero, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    tablero.getTablero()[i][j] = ' '; // Deshacer movimiento

                    if (valorMovimiento > mejorValor) {
                        mejorValor = valorMovimiento;
                        mejorMovimiento[0] = i;
                        mejorMovimiento[1] = j;
                    }
                }
            }
        }
        return mejorMovimiento;
    }

    private int minimax(Tablero tablero, int profundidad, boolean esMaximizador, int alfa, int beta) {
        int[][] ganador = tablero.revisarTablero();
        if (ganador != null) {
            char simboloGanador = tablero.getTablero()[ganador[0][0]][ganador[0][1]];
            if (simboloGanador == pc) return 10 - profundidad;
            if (simboloGanador == jugador) return profundidad - 10;
        }

        if (tablero.estaLleno()) {
            return 0; // Empate
        }

        if (esMaximizador) {
            int mejorValor = Integer.MIN_VALUE;
            for (int i = 0; i < Tablero.SIZE; i++) {
                for (int j = 0; j < Tablero.SIZE; j++) {
                    if (tablero.getTablero()[i][j] == ' ') {
                        tablero.colocarMarca(i, j, pc);
                        int valor = minimax(tablero, profundidad + 1, false, alfa, beta);
                        tablero.getTablero()[i][j] = ' ';

                        mejorValor = Math.max(mejorValor, valor);
                        alfa = Math.max(alfa, mejorValor);

                        if (beta <= alfa) {
                            break; // Poda beta: no seguir explorando más hijos
                        }
                    }
                }
                if (beta <= alfa) break; // Salir del bucle externo también si hay poda
            }
            return mejorValor;
        } else {
            int mejorValor = Integer.MAX_VALUE;
            for (int i = 0; i < Tablero.SIZE; i++) {
                for (int j = 0; j < Tablero.SIZE; j++) {
                    if (tablero.getTablero()[i][j] == ' ') {
                        tablero.colocarMarca(i, j, jugador);
                        int valor = minimax(tablero, profundidad + 1, true, alfa, beta);
                        tablero.getTablero()[i][j] = ' ';

                        mejorValor = Math.min(mejorValor, valor);
                        beta = Math.min(beta, mejorValor);

                        if (beta <= alfa) {
                            break; // Poda alfa
                        }
                    }
                }
                if (beta <= alfa) break; // Salir del bucle externo si hay poda
            }
            return mejorValor;
        }
    }
}


