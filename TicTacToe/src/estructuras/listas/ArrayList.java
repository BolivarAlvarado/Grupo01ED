/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.listas;

import java.util.Iterator;
import tda.List;

/**
 * Implementación de ArrayList
 *
 * @param <E> Tipo de datos que almacenará la lista.
 * @author Michelle
 */
public class ArrayList<E> implements List<E>, Iterable<E> {

    /**
     * Arreglo donde se almacenan los elementos.
     */
    private E[] array;
    /**
     * Capacidad máxima actual del arreglo.
     */
    private int capacity = 10;
    /**
     * Número de elementos almacenados en la lista.
     */
    private int efectivo;

    /**
     * Constructor que inicializa la lista con una capacidad de 10 elementos.
     */
    public ArrayList() {
        array = (E[]) new Object[capacity];
        efectivo = 0;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return {@code true} si la lista no contiene elementos, {@code false} en
     * caso contrario.
     */
    @Override
    public boolean isEmpty() {
        return efectivo == 0;
    }

    /**
     * Obtiene el número de elementos en la lista.
     *
     * @return cantidad de elementos almacenados.
     */
    @Override
    public int size() {
        return efectivo;
    }

    /**
     * Inserta un elemento al inicio de la lista.
     *
     * @param element elemento a insertar.
     * @return {@code true} si se insertó correctamente, {@code false} si el
     * elemento es {@code null}.
     */
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (efectivo == capacity) {
            addCapacity();
        }
        desplazar(0);
        array[0] = element;
        efectivo++;
        return true;
    }

    /**
     * Desplaza los elementos hacia la derecha desde un índice dado.
     *
     * @param index posición desde donde iniciar el desplazamiento.
     */
    private void desplazar(int index) {
        for (int i = efectivo - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
    }

    /**
     * Inserta un elemento al final de la lista.
     *
     * @param element elemento a insertar.
     * @return {@code true} si se insertó correctamente, {@code false} si es
     * {@code null}.
     */
    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (efectivo == capacity) {
            addCapacity();
        }
        array[efectivo++] = element;
        return true;
    }

    /**
     * Aumenta la capacidad del arreglo en un 50% adicional.
     */
    private void addCapacity() {
        E[] temp = (E[]) new Object[efectivo + (efectivo / 2)];
        for (int i = 0; i < efectivo; i++) {
            temp[i] = array[i];
        }
        array = temp;
        capacity = efectivo + (efectivo / 2);
    }

    /**
     * Elimina el primer elemento de la lista.
     *
     * @return {@code true} si se eliminó correctamente, {@code false} si la
     * lista está vacía.
     */
    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        desplazarRevers(0);
        efectivo--;
        return true;
    }

    /**
     * Desplaza elementos hacia la izquierda desde un índice dado.
     *
     * @param index índice desde donde iniciar el desplazamiento.
     */
    private void desplazarRevers(int index) {
        for (int i = index; i < efectivo - 1; i++) {
            array[i] = array[i + 1];
        }
        array[efectivo - 1] = null;
    }

    /**
     * Elimina el último elemento de la lista.
     *
     * @return {@code true} si se eliminó correctamente, {@code false} si está
     * vacía.
     */
    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }
        array[efectivo - 1] = null;
        efectivo--;
        return true;
    }

    /**
     * Elimina la primera aparición de un elemento específico.
     *
     * @param element elemento a eliminar.
     * @return {@code true} si se eliminó, {@code false} si no se encontró.
     */
    @Override
    public boolean remove(E element) {
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < efectivo; i++) {
            if (array[i] == element) { // Comparación por referencia
                desplazarRevers(i);
                efectivo--;
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un elemento en un índice específico.
     *
     * @param index posición del elemento a eliminar.
     * @return elemento eliminado, o {@code null} si el índice no es válido.
     */
    @Override
    public E remove(int index) {
        if (isEmpty() || (!(index >= 0 && index < efectivo))) {
            return null;
        }
        E temp = array[index];
        desplazarRevers(index);
        array[efectivo - 1] = null;
        efectivo--;
        return temp;
    }

    /**
     * Reemplaza el elemento en un índice dado.
     *
     * @param index posición a modificar.
     * @param element nuevo valor.
     * @return elemento anterior, o {@code null} si el índice no es válido.
     */
    @Override
    public E set(int index, E element) {
        if (isEmpty()) {
            return null;
        } else if (index >= 0 && index < efectivo) {
            E temp = array[index];
            array[index] = element;
            return temp;
        }
        return null;
    }

    /**
     * Representación en cadena de la lista.
     *
     * @return texto con los elementos entre corchetes.
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < efectivo; i++) {
            sb.append(array[i]);
            if (i < efectivo - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Obtiene el primer elemento de la lista.
     *
     * @return primer elemento, o {@code null} si está vacía.
     */
    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return array[0];
        }
    }

    /**
     * Obtiene el último elemento de la lista.
     *
     * @return último elemento, o {@code null} si está vacía.
     */
    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        } else {
            return array[efectivo - 1];
        }
    }

    /**
     * Inserta un elemento en una posición específica.
     *
     * @param index índice donde se insertará.
     * @param element elemento a insertar.
     * @return {@code true} si se insertó, {@code false} si el índice no es
     * válido o el elemento es {@code null}.
     */
    @Override
    public boolean insert(int index, E element) {
        if (element == null || index < 0 || index > efectivo) {
            return false;
        }
        if (efectivo == capacity) {
            addCapacity();
        }
        desplazar(index);
        array[index] = element;
        efectivo++;
        return true;
    }

    /**
     * Verifica si un elemento existe en la lista.
     *
     * @param element elemento a buscar.
     * @return {@code true} si existe, {@code false} en caso contrario.
     */
    @Override
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < efectivo; i++) {
                if (array[i] == element) { // Comparación por referencia
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Compara esta lista con otra para verificar si son iguales.
     *
     * @param o objeto a comparar.
     * @return {@code true} si tienen los mismos elementos en el mismo orden.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ArrayList)) {
            return false;
        }
        ArrayList<E> other = (ArrayList<E>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.efectivo; i++) {
            if (!this.array[i].equals(other.array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Obtiene un elemento por su índice.
     *
     * @param index posición del elemento.
     * @return elemento en esa posición, o {@code null} si no es válido.
     */
    public E get(int index) {
        if (isEmpty()) {
            return null;
        } else if (index >= 0 && index < efectivo) {
            return array[index];
        }
        return null;
    }

    /**
     * Retorna un iterador para recorrer la lista.
     *
     * @return iterador de tipo {@code ArrayListIterator}.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Clase interna que implementa el iterador para recorrer la lista.
     */
    private class ArrayListIterator implements Iterator<E> {

        /**
         * Índice del siguiente elemento a devolver.
         */
        private int current = 0;

        /**
         * Verifica si existen más elementos por recorrer.
         *
         * @return {@code true} si hay más elementos, {@code false} si no.
         */
        @Override
        public boolean hasNext() {
            return current < efectivo;
        }

        /**
         * Devuelve el siguiente elemento de la lista.
         *
         * @return siguiente elemento.
         */
        @Override
        public E next() {
            return array[current++];
        }
    }
}
