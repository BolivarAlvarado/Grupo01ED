/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.listas;

import java.util.Objects;
import tda.List;

/**
 *
 * @author Michelle
 * @param <E> Tipo de dato que almacena la lista
 */
public class LinkedList<E> implements List<E> {

    private Nodo<E> first;
    private Nodo<E> last;
    private int efectivo;

    public LinkedList() {
        efectivo = 0;
        first = last = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }

    @Override
    public int size() {
        return efectivo;
    }

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

    @Override
    public boolean addlast(E element) {
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

    @Override
    public E getFirst() {
        if (isEmpty()) {
            return null;
        }
        return first.getData();
    }

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

    @Override
    public E getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.getData();
    }

    @Override
    public boolean insert(int index, E element) {
        if (element == null || index < 0 || index > efectivo) {
            return false;
        }

        if (index == 0) {
            return addFirst(element);
        }

        if (index == efectivo) {
            return addlast(element);
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.first);
        hash = 97 * hash + this.efectivo;
        return hash;
    }
}
