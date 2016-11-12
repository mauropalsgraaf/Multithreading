package data_structures.implementation;

import java.util.ArrayList;

import data_structures.Sorted;
import data_structures.implementation.coarsegrainedtree.Node;

public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {

    private Node<T> left;
    private Node<T> right;

    public void add(T t) {
    }

    public void remove(T t) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<T> toArrayList() {
        throw new UnsupportedOperationException();
    }
}
