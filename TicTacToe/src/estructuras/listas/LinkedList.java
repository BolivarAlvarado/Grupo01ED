/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.listas;

import java.util.Iterator;
import java.util.Objects;
import tda.List;

/**
 * Implementación de una lista enlazada simple que utiliza nodos. Soporta
 * operaciones básicas de inserción, eliminación, acceso y búsqueda.
 *
 * @author Michelle
 * @param <E> Tipo de dato que almacena la lista
 */
public class LinkedList<E> implements List<E>, Iterable<E> {

    private Nodo<E> first;   // Primer nodo de la lista
    private Nodo<E> last;    // Último nodo de la lista
    private int efectivo;    // Cantidad de elementos en la lista

    /**
     * Constructor que inicializa una lista vacía.
     */
    public LinkedList() {
        efectivo = 0;
        first = last = null;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si no contiene elementos
     */
    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    /**
     * Devuelve el número de elementos en la lista.
     *
     * @return tamaño de la lista
     */
    @Override
    public int size() {
        return efectivo;
    }

    /**
     * Agrega un elemento al inicio de la lista.
     *
     * @param element el elemento a insertar
     * @return true si fue insertado correctamente
     */
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }
        Nodo<E> node = new Nodo<>(element);
        if (isEmpty()) {
            first = last = node;
        } else {
            node.setNext(first);
            first = node;
        }
        efectivo++;
        return true;
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param element el elemento a insertar
     * @return true si fue insertado correctamente
     */
    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        }
        Nodo<E> node = new Nodo<>(element);
        if (isEmpty()) {
            first = last = node;
        } else {
            last.setNext(node);
            last = node;
        }
        efectivo++;
        return true;
    }

    /**
     * Elimina el primer elemento de la lista.
     *
     * @return true si se eliminó correctamente
     */
    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        } else if (first == last) {
            first = last = null;
        } else {
            Nodo<E> nodeTemp = first;
            first = first.getNext();
            nodeTemp.setNext(null);
            nodeTemp.setData(null);
        }
        efectivo--;
        return true;
    }

    /**
     * Elimina el último elemento de la lista.
     *
     * @return true si se eliminó correctamente
     */
    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        } else if (first == last) {
            first = last = null;
        } else {
            Nodo<E> p = getPrevious(last);
            last.setData(null); //esto es para ayudar al garbage collector
            p.setNext(null);
            last = p;
        }
        efectivo--;
        return true;
    }

    /**
     * Elimina la primera ocurrencia del elemento dado.
     *
     * @param element el elemento a eliminar
     * @return true si se encontró y eliminó
     */
    @Override
    public boolean remove(E element) {
        if (isEmpty() || element == null) {
            return false;
        }

        Nodo<E> actual = first;
        Nodo<E> anterior = null;

        while (actual != null) {
            if (element.equals(actual.getData())) {
                if (actual == first) {
                    return removeFirst();
                } else if (actual == last) {
                    return removeLast();
                } else if (anterior != null) {
                    anterior.setNext(actual.getNext());
                    actual.setNext(null);
                    actual.setData(null);
                    efectivo--;
                    return true;
                }
            }
            anterior = actual;
            actual = actual.getNext();
        }

        return false;
    }

    /**
     * Elimina el elemento en la posición especificada.
     *
     * @param index posición del elemento a eliminar
     * @return el elemento eliminado, o null si índice inválido
     */
    @Override
    public E remove(int index) {
        if (isEmpty() || index < 0 || index >= efectivo) {
            return null;
        }

        if (index == 0) {
            E data = first.getData();
            removeFirst();
            return data;
        }

        Nodo<E> anterior = null;
        Nodo<E> actual = first;
        int i = 0;

        while (i < index) {
            anterior = actual;
            actual = actual.getNext();
            i++;
        }

        E data = actual.getData();

        if (actual == last) {
            removeLast();
        } else if (anterior != null) {
            anterior.setNext(actual.getNext());
            actual.setNext(null);
            actual.setData(null);
            efectivo--;
        }

        return data;
    }

    /**
     * Reemplaza el elemento en la posición indicada.
     *
     * @param index posición a reemplazar
     * @param element nuevo elemento
     * @return el elemento que fue reemplazado, o null si índice inválido
     */
    @Override
    public E set(int index, E element) {
        if (isEmpty() || element == null || !(index >= 0 && index < efectivo)) {
            return null;
        }
        int i = 0;
        for (Nodo<E> p = first; p != null; p = p.getNext()) {
            if (index == i) {
                E temp = p.getData();
                p.setData(element);
                return temp;
            }
            ++i;
        }
        return null;
    }

    /**
     * Retorna el primer elemento de la lista sin eliminarlo.
     *
     * @return primer elemento o null si está vacía
     */
    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.getData();
    }

    /**
     * Método auxiliar para obtener el nodo anterior al nodo dado.
     *
     * @param node el nodo de referencia
     * @return el nodo anterior, o null si es el primero
     */
    private Nodo<E> getPrevious(Nodo<E> node) {
        if (node == first) {
            return null;
        }
        for (Nodo<E> p = first; p != null; p = p.getNext()) {
            if (p.getNext() == node) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retorna el último elemento de la lista sin eliminarlo.
     *
     * @return último elemento o null si está vacía
     */
    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.getData();
    }

    /**
     * Inserta un elemento en la posición dada.
     *
     * @param index posición donde se insertará
     * @param element elemento a insertar
     * @return true si fue insertado exitosamente
     */
    @Override
    public boolean insert(int index, E element) {
        if (element == null || index < 0 || index > efectivo) {
            return false;
        }

        if (index == 0) {
            return addFirst(element);
        }

        if (index == efectivo) {
            return addLast(element);
        }

        Nodo<E> nuevo = new Nodo<>(element);
        Nodo<E> actual = first;
        Nodo<E> anterior = null;
        int i = 0;

        while (i < index) {
            anterior = actual;
            actual = actual.getNext();
            i++;
        }

        if (anterior != null) {
            anterior.setNext(nuevo);
            nuevo.setNext(actual);
            efectivo++;
            return true;
        }

        return false;
    }

    /**
     * Verifica si la lista contiene el elemento especificado.
     *
     * @param element elemento a buscar
     * @return true si se encuentra, false si no
     */
    @Override
    public boolean contains(E element) {
        if (isEmpty() || element == null) {
            return false;
        }
        for (Nodo<E> p = first; p != null; p = p.getNext()) {
            if (p.getData().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna una representación en cadena de la lista.
     *
     * @return los elementos de la lista entre corchetes y separados por comas
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Nodo<E> p = first; p != null; p = p.getNext()) {
            sb.append(p.getData()).append(",");
        }
        return sb.substring(0, sb.length() - 1) + "]";
    }

    /**
     * Verifica si otra lista es igual a esta (mismos elementos).
     *
     * @param o la otra lista a comparar
     * @return true si son iguales
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof LinkedList)) {
            return false;
        }
        LinkedList<E> other = (LinkedList<E>) o;
        if (other.efectivo != this.efectivo) {
            return false;
        }
        Nodo<E> q = other.first;
        for (Nodo<E> p = this.first; p != null; p = p.getNext()) {
            if (!(p.getData().equals(q.getData()))) {
                return false;
            }
            q = q.getNext();
        }
        return true;
    }

    /**
     * Retorna un código hash basado en los nodos y tamaño.
     *
     * @return hashCode de la lista
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.first);
        hash = 97 * hash + this.efectivo;
        return hash;
    }

   @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Nodo<E> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
