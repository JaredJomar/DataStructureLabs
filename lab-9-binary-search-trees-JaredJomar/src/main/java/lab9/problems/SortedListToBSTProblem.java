package lab9.problems;

import java.util.LinkedList;
import java.util.List;

import lab9.interfaces.Entry;
import lab9.tree.BTEntry;
import lab9.tree.BTNode;

public class SortedListToBSTProblem<K extends Comparable<? super K>, V> {
	/**
	 * TODO EXERCISE 3:
	 * 
	 * Write a method named sortedListToBST(). Given a linked list where elements
	 * are sorted in ascending order, convert it to a height balanced Binary Search
	 * Tree (BST).
	 * 
	 * The method receives as parameter a non-empty list with integers sorted in
	 * increasing (ascending) order, and returns the root of the Binary Search Tree
	 * that is the equivalent of the sorted list.
	 * 
	 * Note: The tester will use the method isBalanced() (see given code) to check
	 * if the tree is balanced.
	 * 
	 * Hint 1: Recall binary search on an array of integers. Divide & Conquer
	 * 
	 * @param <K> Comparable Keys of each Key-Value pair of BST, as well as the
	 *            elements of the list
	 * @param <V> Values mapped to each key in the BST
	 * @param L   List of values of type K in sorted order
	 * @return Root of Binary Search Tree with values of L correctly placed
	 */
	private BTNode<K, V> sortedListToBST(List<K> L, int left, int right) {
		/* TODO ADD YOUR CODE HERE */
		if (left > right)
			return null;

		int mid = (left + right) / 2;
		K value = L.get(mid);
		BTEntry<K, V> entry = new BTEntry<>(value, null);

		BTNode<K, V> leftNode = sortedListToBST(L, left, mid - 1);
		BTNode<K, V> rightNode = sortedListToBST(L, mid + 1, right);

		BTNode<K, V> node = new BTNode<>(entry, null, leftNode, rightNode);
		return node;
	}

	public BTNode<K, V> sortedListToBST(List<K> L) {
		return sortedListToBST(L, 0, L.size() - 1);
	}

	/************************************
	 * DO NOT EDIT. THIS IS FOR TESTING *
	 ************************************/
	public boolean isBalanced(BTNode<K, V> root) {
		if (root == null || isLeaf(root))
			return true;
		else
			return Math.abs(height(root.getLeftChild()) - height(root.getRightChild())) <= 1;
	}

	public int height(BTNode<K, V> root) {
		if (root == null)
			return 0;
		else if (root.getLeftChild() == null && root.getRightChild() == null)
			return 0;
		else
			return 1 + Math.max(height(root.getLeftChild()), height(root.getRightChild()));
	}

	private boolean isLeaf(BTNode<K, V> node) {
		return node.getLeftChild() == null && node.getRightChild() == null;
	}

	public List<K> flatten(BTNode<K, V> root) {
		List<K> L = new LinkedList<>();
		recFlatten(root, L);
		return L;
	}

	private void recFlatten(BTNode<K, V> root, List<K> L) {
		if (root == null)
			return;
		else {
			recFlatten(root.getLeftChild(), L);
			L.add(root.getEntry().getKey());
			recFlatten(root.getRightChild(), L);
		}
	}
}
