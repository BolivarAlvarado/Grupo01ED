/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.arbol;

/**
 *
 * @author Michelle
 */
public class ArbolN<E> {

    private NodoA raiz;

    public ArbolN(E datoRaiz) {
        this.raiz = new NodoA<>(datoRaiz);
    }

    public NodoA<E> getRaiz() {
        return raiz;
    }

    public boolean add(E child, E parent) {
        NodoA<E> nodoPadre = buscarNodo(raiz, parent);
        if (nodoPadre != null) {
            nodoPadre.agregarHijo(new NodoA<>(child));
            return true;
        }
        return false; // No se encontró el nodo padre
    }

    private NodoA<E> buscarNodo(NodoA<E> actual, E valor) {
        if (actual.getDato().equals(valor)) {
            return actual;
        }

        for (NodoA<E> hijo : actual.getHijos()) {
            NodoA<E> resultado = buscarNodo(hijo, valor);
            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(NodoA<E> nodo, int nivel) {
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(nodo.getDato());

        for (NodoA<E> hijo : nodo.getHijos()) {
            imprimirRecursivo(hijo, nivel + 1);
        }
    }
}
