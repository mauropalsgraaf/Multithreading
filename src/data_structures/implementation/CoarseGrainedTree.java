package data_structures.implementation;

import java.util.ArrayList;

import data_structures.Sorted;
import data_structures.implementation.coarsegrainedtree.Node;

public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {

    private Node<T> top;

    public void add(T value) {
        Node<T> nodeToAdd = new Node<>(value);

        if (top == null) {
            top = nodeToAdd;

            return;
        }

        top.add(nodeToAdd);
    }

    public void remove(T value) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<T> toArrayList() {
        
    }
}
