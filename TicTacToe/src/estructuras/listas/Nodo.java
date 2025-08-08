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
    
    // Valor almacenado en el nodo
    private E data;
    
    // Enlace al siguiente nodo
    private Nodo<E> next;

    /**
     * Constructor que recibe el dato a almacenar.
     * @param data Elemento que guarda el nodo
     */
    public Nodo(E data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Establece el dato del nodo.
     * @param data Nuevo valor a guardar
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Establece la referencia al siguiente nodo.
     * @param next Nodo siguiente en la lista
     */
    public void setNext(Nodo<E> next) {
        this.next = next;
    }

    /**
     * Obtiene el dato almacenado.
     * @return Elemento guardado en el nodo
     */
    public E getData() {
        return data;
    }

    /**
     * Obtiene el siguiente nodo enlazado.
     * @return Nodo siguiente
     */
    public Nodo<E> getNext() {
        return next;
    }
}