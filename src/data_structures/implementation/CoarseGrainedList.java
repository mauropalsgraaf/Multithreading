package data_structures.implementation;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;

public class CoarseGrainedList<T extends Comparable<T>> implements Sorted<T> {
	
    private Node<T> head = new Node<>(null);
    private Lock lock = new ReentrantLock();

    public void add(T value) {
        Node<T> nodeToAdd = new Node<>(value);

        lock.lock();
        try {
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
        } finally {
            lock.unlock();
        }
    }

    public void remove(T value) {
        lock.lock();

        try {
            Node<T> previous = head;
            Node<T> nodeToCompare = head.getNext();

            while (nodeToCompare != null) {
                int nodeComparator = value.compareTo(nodeToCompare.getValue());

                if (nodeComparator == 0) {
                    previous.setNext(nodeToCompare.getNext());

                    return;
                }

                if (nodeComparator < 0) {
                    return;
                }

                previous = nodeToCompare;
                nodeToCompare = nodeToCompare.getNext();
            }
        } finally {
            lock.unlock();
        }
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();

        Node<T> nodeToCompare = head.getNext();

        while (nodeToCompare != null) {
            list.add(nodeToCompare.getValue());

            nodeToCompare = nodeToCompare.getNext();
        }

        return list;
    }

    private class Node<T> {
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
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
    }
}
