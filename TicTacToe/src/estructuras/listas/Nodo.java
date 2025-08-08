/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.listas;

/**
 * Nodo genérico para lista enlazada.
 *
 * @param <E> Tipo de dato que almacena el nodo.
 */
public class Nodo<E> {

    private E data;
    
    private Nodo<E> next;

    public Nodo(E data) {
        this.data = data;
        this.next = null;
    }

    public void setData(E data) {
        this.data = data;
    }

    public void setNext(Nodo<E> next) {
        this.next = next;
    }

    public E getData() {
        return data;
    }

    public Nodo<E> getNext() {
        return next;
    }
}
