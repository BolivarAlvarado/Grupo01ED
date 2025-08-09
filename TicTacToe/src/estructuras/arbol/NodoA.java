/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.arbol;

import estructuras.listas.ArrayList;
import estructuras.listas.LinkedList;

/**
 * Clase que representa un nodo en un árbol, donde cada nodo puede tener varios
 * hijos.
 *
 * @param <E> Tipo del dato almacenado en el nodo.
 * @author Michelle
 */
class NodoA<E> {

    private E dato;  // Dato almacenado en el nodo
    private ArrayList<NodoA<E>> hijos;  // Lista de hijos del nodo

    /**
     * Constructor para crear un nodo con un dato específico.
     *
     * @param dato Dato que se almacenará en el nodo.
     */
    public NodoA(E dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();  // Inicializa la lista de hijos como vacía
    }

    /**
     * Obtiene el dato almacenado en el nodo.
     *
     * @return El dato almacenado en el nodo.
     */
    public E getDato() {
        return dato;
    }

    /**
     * Establece un nuevo valor para el dato almacenado en el nodo.
     *
     * @param dato Nuevo dato que se almacenará en el nodo.
     */
    public void setDato(E dato) {
        this.dato = dato;
    }

    /**
     * Obtiene la lista de hijos del nodo.
     *
     * @return Lista de hijos del nodo.
     */
    public ArrayList<NodoA<E>> getHijos() {
        return hijos;
    }

    /**
     * Agrega un hijo al nodo.
     *
     * @param hijo El nodo hijo que se agregará.
     */
    public void agregarHijo(NodoA<E> hijo) {
        hijos.addLast(hijo);  // Agrega el hijo al final de la lista de hijos
    }

    /**
     * Verifica si el nodo tiene hijos.
     *
     * @return true si el nodo tiene al menos un hijo, false en caso contrario.
     */
    public boolean tieneHijos() {
        return !hijos.isEmpty();  // Devuelve true si la lista de hijos no está vacía
    }
}
