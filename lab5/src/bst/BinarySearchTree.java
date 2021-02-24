package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;
  private ArrayList<E> sortList;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
		comparator = ((e1,e2) -> ((Comparable <E>) e1).compareTo(e2));
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (x == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} 
		return add(root, x);
	}
	private boolean add(BinaryNode<E> n, E x) {
		int compare = comparator.compare(x, n.element); 
		if (compare == 0 ) {
			return false;
		} else if (compare < 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(n.left, x);
				}
			} else {
				if (n.right == null) {
					n.right = new BinaryNode<E>(x);
					size++;
					return true;
			}else {
				return add(n.right,x);
			}
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	private int height(BinaryNode<E> n) {
		if (root==null) {
			return 0;
		}
		if (n == null) {
			return -1;
		} else {
			return Math.max(height(n.left), height(n.right)) + 1 ;
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		 print(root);
	}
	private void print(BinaryNode<E>n) {
		if (n != null) {
			print(n.left);
			System.out.println(n.element);
			print(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		sortList = new ArrayList<>();
		toArray(root, sortList);
		root = buildTree(sortList, 0, size-1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if(n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last ) {
			return null;
		}
		if(first == last) {
			return new BinaryNode<E>(sorted.get(first));
		} else {
			int mid = (first + last) /2;
			BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
			node.left = buildTree(sorted, first, mid-1);
			node.right = buildTree(sorted, mid+1, last);
			return node;
			}
		}


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	public static void main (String[] args) {
		BinarySearchTree <Integer> bst = new BinarySearchTree<Integer>();
		for(int n = 10; n <= 20; n++) {
			bst.add(n);
		}
		for(int n = 1; n <= 9; n++) {
			bst.add(n);
		}
	
		
		BSTVisualizer bstV = new BSTVisualizer ("BST", 300,300);
		
		bst.rebuild();
		bst.printTree();
		bstV.drawTree(bst);
	}
	
}
