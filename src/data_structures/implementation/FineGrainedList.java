package data_structures.implementation;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;

public class FineGrainedList<T extends Comparable<T>> implements Sorted<T> {

    private Node<T> head;
    private Node<T> tail;

    public FineGrainedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);

        this.head.setNext(tail);
    }

    public void add(T value) {
        Node<T> nodeToAdd = new Node<>(value);

        head.lock();
        Node<T> previous = head;

        try {
            Node<T> nodeToCompare = head.getNext();
            nodeToCompare.lock();

            try {
                //Continue till the nodeToCompare is tail, there are several ways to jump out in the while loop.
                while (!nodeToCompare.equals(this.tail)) {

                    if (value.compareTo(nodeToCompare.getValue()) <= 0) {
                        previous.setNext(nodeToAdd);
                        nodeToAdd.setNext(nodeToCompare);

                        return;
                    }

                    previous.unlock();
                    previous = nodeToCompare;
                    nodeToCompare = nodeToCompare.getNext();
                    nodeToCompare.lock();
                }

                //If the item is the last in the list, connect the previous to nodeToAdd and nodeToAdd to tail
                previous.setNext(nodeToAdd);
                nodeToAdd.setNext(nodeToCompare);

            } finally {
                nodeToCompare.unlock();
            }
        } finally {
            previous.unlock();
        }
    }

    public void remove(T value) {
        Node<T> previous = null, nodeToCompare = null;
        head.lock();

        try {
            previous = head;
            nodeToCompare = head.getNext();
            nodeToCompare.lock();

            try {
                while (!nodeToCompare.equals(this.tail)) {
                    int nodeComparator = value.compareTo(nodeToCompare.getValue());

                    if (nodeComparator == 0) {
                        previous.setNext(nodeToCompare.getNext());

                        return;
                    }

                    if (nodeComparator < 0) {
                        return;
                    }

                    previous.unlock();
                    previous = nodeToCompare;
                    nodeToCompare = nodeToCompare.getNext();
                    nodeToCompare.lock();
                }
            } finally {
                nodeToCompare.unlock();
            }
        } finally {
            previous.unlock();
        }

    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();

        Node<T> nodeToCompare = head.getNext();

        while (nodeToCompare != null && nodeToCompare != tail) {
            list.add(nodeToCompare.getValue());

            nodeToCompare = nodeToCompare.getNext();
        }

        return list;
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
