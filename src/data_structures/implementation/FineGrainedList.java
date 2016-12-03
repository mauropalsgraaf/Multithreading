package data_structures.implementation;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;

public class FineGrainedList<T extends Comparable<T>> implements Sorted<T> {

    private Node<T> head;

    public void add(T value) {
        Node<T> nodeToAdd = new Node<>(value);

        Node<T> previous = head;
        Node<T> nodeToCompare = head.getNext();

        while (nodeToCompare != null) {
            if (value.compareTo(nodeToCompare.getValue()) <= 0) {
                previous.setNext(nodeToAdd);
                nodeToAdd.setNext(nodeToCompare);

                return;
            }

            previous = nodeToCompare;
            nodeToCompare = nodeToCompare.getNext();
        }

        previous.setNext(nodeToAdd);

    }

    public void remove(T value) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<T> toArrayList() {
        throw new UnsupportedOperationException();
    }

    private class Node<T> {
        private Node<T> next;
        private T value;
        private Lock lock;

        public Node(T value) {
            this.value = value;
            this.lock = new ReentrantLock();
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void lock() {
            this.lock.lock();
        }

        public void unlock() {
            this.lock.unlock();
        }
    }
}
