package main;

import estructuras.arbol.ArbolN;
import main.modelo.Juego;

public class TicTacToe {

    public static void main(String[] args) {        
        crearArbol();
        Juego juego = new Juego();

        juego.comenzarJuego();
    }

    private static void crearArbol() {
        ArbolN<String> arbol = new ArbolN<>("Raiz");

        arbol.add("Hijo 1", "Raiz");
        arbol.add("Hijo 2", "Raiz");
        arbol.add("Hijo 3", "Raiz");

        arbol.add("Nieto 1.1", "Hijo 1");
        arbol.add("Nieto 1.2", "Hijo 1");

        arbol.add("Nieto 2.1", "Hijo 2");
        arbol.add("Nieto 2.2", "Hijo 2");
        arbol.add("Nieto 2.3", "Hijo 2");
        arbol.add("Nieto 2.4", "Hijo 2");

        arbol.add("Nieto 1.1.1", "Nieto 1.1");
        arbol.add("Nieto 1.1.2", "Nieto 1.1");

        arbol.imprimir();
        System.out.println("");
    }
}
