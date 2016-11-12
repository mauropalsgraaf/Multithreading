package data_structures.implementation.coarsegrainedtree;

public class Node<T extends Comparable<T>> {
    private Node<T> left;
    private Node<T> right;
    private T value;

    public Node(T value) {
        this.value = value;
    }

    public void add(Node<T> node) {
        if (node.getValue().compareTo(value) < 0) {
            if (left == null) {
                left = node;

                return;
            }

            left.add(node);

            return;
        }

        if (right == null) {
            right = node;

            return;
        }

        right.add(node);
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getValue() {
        return value;
    }
}
