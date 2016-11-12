package data_structures.implementation;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;
import data_structures.implementation.coarsegrainedlist.Node;

public class CoarseGrainedList<T extends Comparable<T>> implements Sorted<T> {

    private Node<T> head;
    private Lock lock = new ReentrantLock();

    public void add(T value) {
        lock.lock();

        try {
            Node<T> nodeToAdd = new Node<>(value);

            if (head == null) { //if list is empty
                head = nodeToAdd;

                return;
            }

            if (value.compareTo(head.getValue()) <= 0) { //if item to add is smaller or equal to head, just add it in front
                nodeToAdd.setNext(head);
                head = nodeToAdd;

                return;
            }

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
            while (head.getValue().compareTo(value) == 0) {
                head = head.getNext();
            }

            Node<T> previous = head;
            Node<T> nodeToCompare = head.getNext();

            while (nodeToCompare != null) {
                int nodeComparator = value.compareTo(nodeToCompare.getValue());

                if (nodeComparator == 0) {
                    previous.setNext(nodeToCompare.getNext());

                    nodeToCompare = previous.getNext();
                    continue;
                }

                if (nodeComparator < 0) {
                    return;
                }

                previous = previous.getNext();
                nodeToCompare = nodeToCompare.getNext();
            }
        } finally {
            lock.unlock();
        }
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        if (head == null) {
            return list;
        }

        list.add(head.getValue());

        Node<T> nodeToCompare = head.getNext();

        while (nodeToCompare != null) {
            list.add(nodeToCompare.getValue());

            nodeToCompare = nodeToCompare.getNext();
        }

        return list;
    }
}
