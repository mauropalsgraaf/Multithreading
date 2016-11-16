package data_structures.implementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import data_structures.Sorted;


public class CoarseGrainedTree<T extends Comparable<T>> implements Sorted<T> {

    private Node root;
    private Lock lock = new ReentrantLock();


    public void add(T value) {
    	lock.lock();
        
    	try {
    		root = add(root, value);
    	} finally {
    		lock.unlock();
    	}
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
    }
    
}
