/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.listas;

import java.util.Iterator;
import tda.List;

/**
 *
 * @author Michelle
 */
public class ArrayList<E> implements List<E>, Iterable<E> {

    private E[] array;
    private int capacity = 10;
    private int efectivo;

    public ArrayList() {
        array = (E[]) new Object[capacity];
        efectivo = 0;
    }

    @Override
    public boolean isEmpty() {
        return efectivo == 0;
    }

    @Override
    public int size() {
        return efectivo;
    }

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

    private void desplazar(int index) {
        for (int i = efectivo - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false; //no se va a permitir ingresar null
        } else if (efectivo == capacity) {
            addCapacity();
        }
        array[efectivo++] = element; //efectivo hará el incremento después de ejecutarse esta línea
        return true;
    }

    private void addCapacity() { //aumentará la mitad de lo que tengo
        E[] temp = (E[]) new Object[efectivo + (efectivo / 2)];
        for (int i = 0; i < efectivo; i++) {
            temp[i] = array[i];
        }
        array = temp; //shallow copy  y lo que tenía el array anteriomente se lo llevó el garbage collector
        capacity = efectivo + (efectivo / 2);

    }

    @Override
    public boolean removeFirst() {
        if (isEmpty() == true) {
            return false;
        }
        desplazarRevers(0);
        efectivo--;
        return true;
    }

    private void desplazarRevers(int index) {
        for (int i = index; i < efectivo - 1; i++) {
            array[i] = array[i + 1];
        }
        array[efectivo - 1] = null;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty() == true) {
            return false;
        }
        array[efectivo - 1] = null;
        efectivo--;
        return true;
    }

    @Override
    public boolean remove(E element) {
        if (isEmpty()) {
            return false;
        }
        for (int i = 0; i < efectivo; i++) {
            if (array[i] == element) {
                desplazarRevers(i);
                efectivo--;
                return true;
            }
        }
        return false;

    }

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

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        } else {
            return array[0];
        }
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        } else {
            return array[efectivo - 1];
        }
    }

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

    @Override
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < efectivo; i++) {
                if (array[i] == element) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof ArrayList)) {
            return false;
        }
        ArrayList<E> other = (ArrayList<E>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < this.efectivo; i++) { //this.efectivo u o.efectivo
            if (!this.array[i].equals(other.array[i])) {
                return false;
            }
        }
        return true;
    }

    public E get(int index) {
        if (isEmpty()) {
            return null;
        } else if (index >= 0 && index < efectivo) {
            return array[index];
        }
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {

        private int current = 0; // índice del siguiente elemento

        @Override
        public boolean hasNext() {
            return current < efectivo;
        }

        @Override
        public E next() {
            return array[current++];
        }
    }

}
