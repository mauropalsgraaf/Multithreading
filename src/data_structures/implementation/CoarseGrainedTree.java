package data_structures.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import data_structures.Sorted;


public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {

    private Node root;

    public void add(T value) {
    	root = add(root, element);
    }
    
    /**
     * Recursive function for adding a value as a node in a BST
     */
    private Node add(Node node, T value) {
    	if (node == null) {
    		return new Node(value);
    	}
    	
    	if(value.compareTo(node.value) <= 0) {
    		node.left = add(node.left, value);
    	} else {
    		node.right = add(node.right, value);
    	}
    	
    	return node;
    }

    public void remove(T value) {
        root = remove(root, value);
    }
    
    private Node remove(Node node, T value) {
    	if (value.compareTo(node.value) < 0) {
    		node.left = remove(node.left, value);
    	} else if (value.compareTo(node.value) > 0) {
    		node.right = remove(node.right, value);
    	} else if (node.left != nul && node.right != null) {
    		node.value = findMaximum(node.left);
    		node.left = remove(node.left, node.value);
    	} else {
    		node = node.left != null ? node.left : node.right;
    	}
    	
    	return node;
    }
    
    private T findMaximum(Node node) {
    	if (node.right == null) {
    		return node.data
    	} else {
    		return findMaximum(node.right);
    	}
    }

    public ArrayList<T> toArrayList() {
        List<T> result = new ArrayList<>();
        
        populateAscendingArrayList(root, result);
        
        return result;
    }
    
    public void populateAscendingArrayList(Node node, List<T> list) {
    	if (node != null) {
    		populateAscendingArrayList(node.left, list);
    		list.add(note.data);
    		populateAscendingArrayList(node.right, list);
    	}
    }
    
    
    private class Node {
    	private T value;
    	private Node left;
    	private Node right;
    	
    	public Node() {
    		this.value = null;
    		this.left = null;
    		this.right = null;
    	}
    	
    	public Node(T value) {
    		this(value, null, null);
    	}
    	
    	public Node(E value, Node left, Node right) {
    		this.value = value;
    		this.left = left; 
    		this.right = right;
    	}
    }
    
}
