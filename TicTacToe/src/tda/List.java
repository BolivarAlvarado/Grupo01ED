/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tda;

/**
 *
 * @author Michelle
 */
/**
 * Interfaz que define una lista genérica con operaciones básicas de inserción,
 * eliminación, acceso y verificación.
 *
 * @param <E> el tipo de elementos que se almacenarán en la lista
 */
public interface List<E> {

    /**
     * Verifica si la lista está vacía.
     * 
     * @return true si la lista no contiene elementos, false en caso contrario.
     */
    boolean isEmpty();

    /**
     * Retorna el número de elementos en la lista.
     * 
     * @return la cantidad de elementos en la lista.
     */
    int size();

    /**
     * Agrega un elemento al inicio de la lista.
     * 
     * @param element el elemento a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     */
    boolean addFirst(E element);

    /**
     * Agrega un elemento al final de la lista.
     * 
     * @param element el elemento a agregar.
     * @return true si se agregó correctamente, false en caso contrario.
     */
    boolean addLast(E element); // Corregido de 'addlast'

    /**
     * Elimina el primer elemento de la lista.
     * 
     * @return true si se eliminó correctamente, false si la lista estaba vacía.
     */
    boolean removeFirst();

    /**
     * Elimina el último elemento de la lista.
     * 
     * @return true si se eliminó correctamente, false si la lista estaba vacía.
     */
    boolean removeLast();

    /**
     * Elimina la primera ocurrencia del elemento especificado.
     * 
     * @param element el elemento a eliminar.
     * @return true si el elemento fue encontrado y eliminado, false en caso contrario.
     */
    boolean remove(E element);

    /**
     * Elimina el elemento ubicado en el índice especificado.
     * 
     * @param index la posición del elemento a eliminar.
     * @return el elemento eliminado o null si esta fuera de rango.
     */
    E remove(int index);

    /**
     * Reemplaza el elemento en la posición dada con uno nuevo.
     * 
     * @param index la posición del elemento a reemplazar.
     * @param element el nuevo elemento a colocar.
     * @return el elemento que fue reemplazado o null si esta fuera de rango.
     */
    E set(int index, E element);

    /**
     * Obtiene el primer elemento de la lista sin eliminarlo.
     * 
     * @return el primer elemento, o null si la lista está vacía.
     */
    E getFirst();

    /**
     * Obtiene el último elemento de la lista sin eliminarlo.
     * 
     * @return el último elemento, o null si la lista está vacía.
     */
    E getLast();

    /**
     * Inserta un elemento en la posición especificada.
     * 
     * @param index la posición donde se insertará el elemento.
     * @param element el elemento a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario. Null si esta fuera de rango
     */
    boolean insert(int index, E element);

    /**
     * Verifica si la lista contiene el elemento especificado.
     * 
     * @param element el elemento a buscar.
     * @return true si la lista contiene el elemento, false en caso contrario.
     */
    boolean contains(E element);
}