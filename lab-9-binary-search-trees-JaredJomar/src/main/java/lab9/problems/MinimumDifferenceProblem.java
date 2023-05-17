package lab9.problems;

import lab9.tree.BTNode;

public class MinimumDifferenceProblem {

	/* TODO DECLARE AS MANY PRIVATE FIELDS AS NECESSARY HERE */
	private int minDiff;
	private Integer prevVal;

	/**
	 * TODO EXERCISE 4
	 * 
	 * Implement a method called getMinimumDifference() that takes in as parameter
	 * the root of a Binary Search Tree and returns an integer denoting the minimum
	 * absolute difference of any two distinct nodes within the Binary Search Tree.
	 * 
	 * Assumptions:
	 * 
	 * 1) The node values in the binary search tree are greater than or equal to
	 * zero. 2) All node values in the binary search tree are unique. 3) The method
	 * will never receive the root of a tree that less than 2 nodes.
	 * 
	 * WARNING: YOU MUST COMPLETE THIS PROBLEM IN O(1) SPACE COMPLEXITY TO RECEIVE
	 * CREDIT
	 * 
	 * @param root Root of a Binary Search Tree
	 * @return The minimum absolute difference of two distinct nodes
	 */
	public int getMinimumDifference(BTNode<Integer, Integer> root) {
		/* TODO ADD YOUR CODE HERE */
		minDiff = Integer.MAX_VALUE;
		prevVal = null;
		inorderTraversal(root);
		return minDiff;
	}

	private void inorderTraversal(BTNode<Integer, Integer> node) {
		if (node == null)
			return;
		inorderTraversal(node.getLeftChild());
		if (prevVal != null)
			minDiff = Math.min(minDiff, node.getEntry().getKey() - prevVal);
		prevVal = node.getEntry().getKey();
		inorderTraversal(node.getRightChild());
	}
}