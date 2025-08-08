/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.arbol;

import estructuras.listas.LinkedList;

/**
 *
 * @author Michelle
 */
class NodoA<E> {
    private E dato;
    private LinkedList<NodoA<E>> hijos;
    
    public NodoA(E dato) {
        this.dato = dato;
        this.hijos = new LinkedList<>();
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public LinkedList<NodoA<E>> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoA<E> hijo) {
        hijos.addLast(hijo);
    }

    public boolean tieneHijos() {
        return !hijos.isEmpty();
    }
}
