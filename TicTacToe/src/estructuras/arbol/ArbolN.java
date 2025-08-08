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

    private Nodo raiz;

    public ArbolN(E datoRaiz) {
        this.raiz = new Nodo<>(datoRaiz);
    }

    public Nodo<E> getRaiz() {
        return raiz;
    }

    public boolean add(E child, E parent) {
        Nodo<E> nodoPadre = buscarNodo(raiz, parent);
        if (nodoPadre != null) {
            nodoPadre.agregarHijo(new Nodo<>(child));
            return true;
        }
        return false; // No se encontró el nodo padre
    }

    private Nodo<E> buscarNodo(Nodo<E> actual, E valor) {
        if (actual.getDato().equals(valor)) {
            return actual;
        }

        for (Nodo<E> hijo : actual.getHijos()) {
            Nodo<E> resultado = buscarNodo(hijo, valor);
            if (resultado != null) {
                return resultado;
            }
        }

        return null;
    }

    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(Nodo<E> nodo, int nivel) {
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");
        }
        System.out.println(nodo.getDato());

        for (Nodo<E> hijo : nodo.getHijos()) {
            imprimirRecursivo(hijo, nivel + 1);
        }
    }
}
