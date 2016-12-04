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
        //discuss in person

        node.lock();

        if (parent != null) {
            parent.unlock();
        }

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
            if (node.right == null) {
                node.right = nodeToAdd;

                node.unlock();

                return;
            }

            add(node.right, value, node);
        }
    }


    public void remove(T value) {
        remove(root, value, null);
    }

    private void remove(Node node, T value, Node parent) {
        node.lock();

        if (root.value == null) {
            node.unlock();

            return;
        }

        if (value.compareTo(node.value) < 0) {

            if (parent != null) {
                parent.unlock();
            }

            if (node.left == null) {
                node.unlock();

                return;
            }

            remove(node.left, value, node);

        } else if (value.compareTo(node.value) > 0) {

            if (parent != null) {
                parent.unlock();
            }

            if (node.right == null) {
                node.unlock();

                return;
            }

            remove(node.right, value, node);

        } else if (node.left != null && node.right != null) {

            node.setValue(findMaximum(node.left));

            if (parent != null) {
                parent.unlock();
            }

            remove(node.left, node.value, node);

        } else {
            if (parent != null) {
                if (parent.value.compareTo(node.value) < 0) {
                    parent.right = node.left != null ? node.left : node.right;
                }
                else {
                    parent.left = node.left != null ? node.left : node.right;
                }

                parent.unlock();
            } else {
                root = new Node();
            }
        }
    }

    private T findMaximum(Node node) {
        node.lock();

        try {
            if (node.right == null) {
                return node.value;
            } else {
                return findMaximum(node.right);
            }
        } finally {
            node.unlock();
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
        private Lock lock = new ReentrantLock();

        public Node() {
            this.value = null;
            this.left = null;
            this.right = null;
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
