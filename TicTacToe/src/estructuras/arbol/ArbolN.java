/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.arbol;

/**
 * @author Michelle
 *
 * Clase que representa un árbol con nodos que pueden tener varios hijos. Cada
 * nodo está representado por la clase NodoA.
 *
 * @param <E> Tipo de dato almacenado en los nodos del árbol.
 */
public class ArbolN<E> {

    private NodoA raiz;  // Nodo raíz del árbol

    /**
     * Constructor que crea un árbol con un nodo raíz que contiene el dato
     * proporcionado.
     *
     * @param datoRaiz Dato que se almacenará en la raíz del árbol.
     */
    public ArbolN(E datoRaiz) {
        this.raiz = new NodoA<>(datoRaiz);
    }

    /**
     * Obtiene el nodo raíz del árbol.
     *
     * @return Nodo raíz del árbol.
     */
    public NodoA<E> getRaiz() {
        return raiz;
    }

    /**
     * Agrega un nodo hijo a un nodo padre en el árbol. Si el nodo padre no
     * existe, no se agrega el hijo.
     *
     * @param child Dato que se almacenará en el nodo hijo.
     * @param parent Dato del nodo padre al que se agregará el hijo.
     * @return true si el hijo fue agregado correctamente, false si el nodo
     * padre no fue encontrado.
     */
    public boolean add(E child, E parent) {
        NodoA<E> nodoPadre = buscarNodo(raiz, parent);  // Busca el nodo padre en el árbol
        if (nodoPadre != null) {
            nodoPadre.agregarHijo(new NodoA<>(child));  // Agrega el hijo al nodo padre
            return true;
        }
        return false;  // No se encontró el nodo padre
    }

    /**
     * Busca un nodo en el árbol que tenga el dato especificado.
     *
     * @param actual Nodo actual desde el cual se realiza la búsqueda.
     * @param valor Dato que se busca en el árbol.
     * @return El nodo que contiene el valor, o null si no se encuentra.
     */
    private NodoA<E> buscarNodo(NodoA<E> actual, E valor) {
        // Si el nodo actual contiene el dato, se retorna este nodo
        if (actual.getDato().equals(valor)) {
            return actual;
        }

        // Si el nodo actual no es el que buscamos, se busca recursivamente en sus hijos
        for (NodoA<E> hijo : actual.getHijos()) {
            NodoA<E> resultado = buscarNodo(hijo, valor);
            if (resultado != null) {
                return resultado;  // Si se encuentra el nodo en alguno de los hijos, se retorna
            }
        }

        return null;  // Si no se encuentra el nodo, se retorna null
    }

    /**
     * Imprime el árbol en una representación jerárquica. Utiliza un enfoque
     * recursivo para imprimir cada nivel del árbol.
     */
    public void imprimir() {
        imprimirRecursivo(raiz, 0);  // Comienza a imprimir desde la raíz
    }

    /**
     * Método recursivo para imprimir un nodo y sus hijos en formato jerárquico.
     *
     * @param nodo Nodo actual a imprimir.
     * @param nivel Nivel de profundidad del nodo (para determinar la sangría).
     */
    private void imprimirRecursivo(NodoA<E> nodo, int nivel) {
        // Imprime la cantidad adecuada de espacios según el nivel de profundidad
        for (int i = 0; i < nivel; i++) {
            System.out.print("  ");  // Cada nivel tiene dos espacios
        }
        // Imprime el dato del nodo
        System.out.println(nodo.getDato());

        // Llama recursivamente para imprimir los hijos del nodo
        for (NodoA<E> hijo : nodo.getHijos()) {
            imprimirRecursivo(hijo, nivel + 1);  // Se incrementa el nivel para los hijos
        }
    }
}
