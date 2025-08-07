/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tda;

/**
 *
 * @author Michelle
 */
public interface List<E> {
 boolean isEmpty();
    int size();
    boolean addFirst(E element);
    boolean addlast(E element);
    boolean removeFirst();
    boolean removeLast();
    boolean remove(E element);
    E remove(int index); // retorna el elemento removido
    E set(int index,E element); //cambia el elemento en la posicion de index y retorna el elemento reemplazado
    E getFirst();
    E getLast();
    boolean insert(int index, E element);
    boolean contains(E element);   
}
