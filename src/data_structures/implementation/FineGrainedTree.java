package data_structures.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;


public class FineGrainedTree<T extends Comparable<T>> implements Sorted<T> {

    private Node root = new Node();
    private Lock lock = new ReentrantLock();


    public void add(T value) {
        add(root, value, null);
    }

    /**
     * Recursive function for adding a value as a node in a BST
     */
    private void add(Node node, T value, Node parent) {
        node.lock();
        if (node.getValue() == null) {
            node.setValue(value);

            node.unlock();
            return;
        }

        Node nodeToAdd = new Node(value);

        if (value.compareTo(node.value) <= 0) {
            if (node.left == null) {
                node.left = nodeToAdd;

                node.unlock();

                return;
            }

            add(node.left, value, node);
        } else {
            add(node.right, value, node);
        }

//        node.lock();
//
//        if (parent != null) {
//            parent.unlock();
//        }
//
//        if (node.getValue() == null) {
//            node.unlock();
//            return new Node(value);
//        }
//
        if (value.compareTo(node.value) <= 0) {
            node.left = add(node.left, value, node);
        } else {
            node.right = add(node.right, value, node);
        }
//
//        return node;
    }


    public void remove(T value) {
        lock.lock();

        try {
            root = remove(root, value);
        } finally {
            lock.unlock();
        }
    }

    private Node remove(Node node, T value) {
        if (value.compareTo(node.value) < 0) {
            node.left = remove(node.left, value);
        } else if (value.compareTo(node.value) > 0) {
            node.right = remove(node.right, value);
        } else if (node.left != null && node.right != null) {
            node.value = findMaximum(node.left);
            node.left = remove(node.left, node.value);
        } else {
            node = node.left != null ? node.left : node.right;
        }

        return node;
    }

    private T findMaximum(Node node) {
        if (node.right == null) {
            return node.value;
        } else {
            return findMaximum(node.right);
        }
    }

    public ArrayList<T> toArrayList() {
        ArrayList<T> result = new ArrayList<>();

        populateAscendingArrayList(root, result);

        return result;
    }

    public void populateAscendingArrayList(Node node, List<T> list) {
        if (node != null) {
            populateAscendingArrayList(node.left, list);
            list.add(node.value);
            populateAscendingArrayList(node.right, list);
        }
    }


    private class Node {
        private T value;
        private Node left;
        private Node right;
        private Lock lock;

        public Node() {
            this.value = null;
            this.left = null;
            this.right = null;
            lock = new ReentrantLock();
        }

        Node(T value) {
            this(value, null, null);
        }

        Node(T value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void lock() {
            this.lock.lock();
        }

        public void unlock() {
            this.lock.unlock();
        }
    }

}
