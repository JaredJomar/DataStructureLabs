package lab8.tree;

import lab8.interfaces.List;
import lab8.interfaces.Tree;
import lab8.interfaces.TreeNode;
import lab8.util.list.LinkedList;

public class BinaryTree<E> implements Tree<E> {

	private BinaryTreeNode<E> root;

	/**
	 * Constructor that takes a root and two nodes denoting the subtrees of the root
	 * 
	 * @param root Root of the tree
	 * @param T1   Left subtree of the root
	 * @param T2   Right subtree of the root
	 */
	public BinaryTree(BinaryTreeNode<E> root, BinaryTree<E> T1, BinaryTree<E> T2) {
		this.root = root;

		if (T1 != null) {
			BinaryTreeNode<E> t1Root = (BinaryTreeNode<E>) T1.root();
			this.root.setLeftChild(t1Root);
			t1Root.setParent(this.root);
		}

		if (T2 != null) {
			BinaryTreeNode<E> t2Root = (BinaryTreeNode<E>) T2.root();
			this.root.setRightChild(t2Root);
			t2Root.setParent(this.root);
		}
	}

	/**
	 * Constructor that sets the root of the tree to the given root
	 * 
	 * @param root Root of the tree
	 */
	public BinaryTree(BinaryTreeNode<E> root) {
		// Delegate to other constructor
		this(root, null, null);
	}

	/**
	 * Default Constructor with empty tree
	 */
	public BinaryTree() {
		this(null, null, null);
	}

	@Override
	public TreeNode<E> root() {
		return this.root;
	}

	/**
	 * Returns the left child of the given node, if it's not null
	 * 
	 * @param p The given node
	 * @return The left child of p if it's not null
	 */
	@Override
	public TreeNode<E> left(TreeNode<E> p) {
		return validate(p).getLeftChild();
	}

	/**
	 * Returns the left child of the given node, if it's not null
	 * 
	 * @param p The given node
	 * @return The right child of p if it's not null
	 */
	@Override
	public TreeNode<E> right(TreeNode<E> p) {
		return validate(p).getRightChild();
	}

	@Override
	public TreeNode<E> sibling(TreeNode<E> p) {
		BinaryTreeNode<E> node = validate(p);
		BinaryTreeNode<E> parent = node.getParent();

		if (parent.getLeftChild() == node)
			return parent.getRightChild();
		else
			return parent.getLeftChild();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return recSize(root);
	}

	/**
	 * Returns the size of the tree, counting the nodes in a pre-order fashion
	 *
	 * @param root The root of the tree
	 * @return The current size of the tree
	 */
	private int recSize(BinaryTreeNode<E> root) {
		if (root == null)
			return 0;
		return 1 + recSize(root.getLeftChild()) + recSize(root.getRightChild());
	}

	private BinaryTreeNode<E> validate(TreeNode<E> p) {
		if (p == null)
			throw new IllegalArgumentException("Node cannot be null");
		return (BinaryTreeNode<E>) p;
	}

	/**
	 * Private method that finds the node in the tree that contains the value given
	 * as a parameter.
	 * 
	 * Note: Remove the SuppressWarnings annotation
	 * 
	 * @param e    Value of node to look for
	 * @param root Root of the current subtree
	 * @return Node that contains the value e
	 */
	@SuppressWarnings("unused")
	private BinaryTreeNode<E> find(E e, BinaryTreeNode<E> root) {
		if (root == null)
			return null;
		else if (root.getValue().equals(e))
			return root;
		else {
			BinaryTreeNode<E> leftSubTreeRoot = find(e, root.getLeftChild());
			return leftSubTreeRoot == null ? find(e, root.getRightChild()) : leftSubTreeRoot;
		}
	}

	/**
	 * TODO EXERCISE 1:
	 * 
	 * Implement a member method for the Tree ADT called height(). The method
	 * computes the height of a node with the value given as parameter. If the tree
	 * does not contain a node with the given value, the method returns -1. If the
	 * node with the given value is a leaf, the method returns 0. You can assume
	 * that the method has no duplicates.
	 * 
	 * @param e Given value to search for node in tree
	 * @return The height of the node with value e
	 */
	@Override
	public int height(E e) {
		/* TODO ADD YOUR CODE HERE */
		BinaryTreeNode<E> node = find(e, root);
		// If the node is null, return -1
		if (node == null)
			return -1;
		// If the node is a leaf, return 0
		else if (node.getLeftChild() == null && node.getRightChild() == null)
			return 0;
		// Otherwise, return 1 + the max height of the left and right subtrees
		else
			return 1 + Math.max(height(node.getLeftChild().getValue()), height(node.getRightChild().getValue()));
	}

	/**
	 * TODO EXERCISE 2:
	 * 
	 * Two elements e1 and e2 are said to be cousins in a Binary Tree if they are
	 * located in nodes that are at the same level (depth) in a Binary Tree.
	 * 
	 * Nodes that are siblings are considered as a special case of cousins.
	 * 
	 * Write a method verifyCousins() which returns true if two values e1, & e2 are
	 * cousins or false otherwise.
	 * 
	 * Assume that the tree has no duplicates and that e1 and e2 are present in the
	 * tree.
	 * 
	 * @param e1 First value
	 * @param e2 Second Value
	 * @return True if e1 & e2 are in nodes located in the same depth, false
	 *         otherwise
	 */
	@Override
	public boolean verifyCousins(E e1, E e2) {
		/* TODO ADD YOUR CODE HERE */
		// If either e1 or e2 is null, return false
		if (e1 == null || e2 == null) {
			return false;
		}

		// Find the nodes for e1 and e2
		BinaryTreeNode<E> node1 = find(e1, root);
		BinaryTreeNode<E> node2 = find(e2, root);

		// If either node is not found, they cannot be cousins
		if (node1 == null || node2 == null) {
			return false;
		}

		// If the nodes have the same parent, they are siblings and therefore cousins
		if (node1.getParent() == node2.getParent()) {
			return true;
		}

		// If the nodes are not siblings, check if they are at the same depth
		int depth1 = findDepth(node1);
		int depth2 = findDepth(node2);

		return depth1 == depth2;
	}

	/**
	 * TODO EXERCISE 3:
	 * 
	 * Complete the following member method for the class BinaryTree<E> called
	 * getLevelCount().
	 * 
	 * The method receives as a parameter an integer level that determines the level
	 * to find all of its nodes.
	 * 
	 * The execution throws IllegalArgumentException in the case that the specified
	 * level is a value that is less or equal to 0.
	 * 
	 * The method returns the number of nodes that are at a given level in the
	 * binary tree in the order in which they would be visited by an In-Order
	 * Traversal of the tree.
	 * 
	 * @param level The given level to count all the nodes in the tree
	 * @return The number of nodes in a given level of the binary tree
	 */
	@Override
	public int getLevelCount(int level) {
		/* TODO ADD YOUR CODE HERE */
		// If the level is less than or equal to 0, throw an exception
		if (level <= 0) {
			throw new IllegalArgumentException("Level must be greater than 0");
		}
		// Otherwise, return the number of nodes at the given level
		return getLevelCount(root, level);
	}

	/**
	 * TODO EXERCISE 4:
	 * 
	 * Complete the following member method for the class BinaryTree<E> called
	 * getLevelElements().
	 * 
	 * The method receives as a parameter an integer level that determines the level
	 * to find all of its nodes.
	 * 
	 * The execution throws IllegalArgumentException in the case that the specified
	 * level is a value that is less or equal to 0.
	 * 
	 * The method returns a List<E> that stores all the nodes that are at a given
	 * level in the binary tree in the order in which they would be visited by an
	 * In-Order Traversal of the tree.
	 * 
	 * @param level The given level to store all the nodes in the tree
	 * @return A list of nodes in a given level of the binary tree
	 */
	@Override
	public List<E> getLevelElements(int level) {
		/* TODO ADD YOUR CODE HERE */
		if (level < 1) {
			throw new IllegalArgumentException("Level must be greater than or equal to 1");
		}

		List<E> nodes = new LinkedList<>();
		getLevelElementsHelper(root, level, nodes);
		return nodes;
	}

	/**
	 * Implement a member method for the Tree ADT called isComplete().
	 * 
	 * The method returns true if the tree is a complete tree, otherwise it returns
	 * false.
	 * 
	 * Recall that a complete tree is a tree that had all of its levels full, except
	 * for maybe the last level. Aside from this, all nodes added to the tree must
	 * be added from left to right in each level.
	 * 
	 * WARNING: YOU MUST IMPLEMENT THIS METHOD USING O(1) SPACE (i.e YOU CANNOT USE
	 * ANY EXTERNAL DATA STRUCTURES TO IMPLEMENT YOUR SOLUTION) (i.e. NO LISTS,
	 * STACKS, QUEUES, ARRAYS, ETC.)
	 * 
	 * @return True if the tree is a complete tree, false otherwise
	 */
	@Override
	public boolean isComplete() {
		/* TODO ADD YOUR CODE HERE */
		if (root == null) {
			return true;
		}
		int expectedNodes = 1;
		int actualNodes = 0;
		boolean gapFound = false;
		BinaryTreeNode<E> node = root;
		while (node != null) {
			if (gapFound && !isLeaf(node)) {
				return false;
			}
			if (node.getLeftChild() == null) {
				gapFound = true;
			} else {
				if (gapFound) {
					return false;
				}
				expectedNodes *= 2;
			}
			if (node.getRightChild() == null) {
				gapFound = true;
			} else {
				if (gapFound) {
					return false;
				}
				expectedNodes = 2 * expectedNodes + 1;
			}
			actualNodes++;
			if (actualNodes == expectedNodes) {
				node = node.getParent().getRightChild();
				expectedNodes /= 2;
			} else {
				node = node.getLeftChild();
			}
		}
		return true;
	}

	/***********************************************************
	 * YOU MUST IMPLEMENT ANY AUXILIARY METHOD YOU MAY NEED. * ADD ALL THE AUXILAIRY
	 * METHODS YOU IMPLEMENT HERE, BELOW * THE EXERCISES *
	 ***********************************************************/
	/**
	 * Helper method to find the depth of a node in the tree
	 * 
	 * @param node The node to find the depth of
	 * @return The depth of the node
	 */
	private int findDepth(BinaryTreeNode<E> node) {
		int depth = 0;
		while (node.getParent() != null) {
			node = node.getParent();
			depth++;
		}
		return depth;
	}

	/**
	 * Recursive helper method to get the number of nodes at a given level in the
	 * tree
	 * 
	 * @param node  The node to start at
	 * @param level The level to count the nodes at
	 * @return The number of nodes at the given level
	 */
	private int getLevelCount(BinaryTreeNode<E> node, int level) {
		if (node == null) {
			return 0;
		}
		if (level == 1) {
			return 1;
		}
		return getLevelCount(node.getLeftChild(), level - 1) + getLevelCount(node.getRightChild(), level - 1);
	}

	/**
	 * Helper method for isComplete() that recursively checks if the tree is a
	 * complete
	 * 
	 * @param node   The current node
	 * @param level  The current level
	 * @param height The height of the tree
	 * @return
	 */

	/**
	 * Helper method to recursively traverse the binary tree and add the elements at
	 * the specified level to the list.
	 * 
	 * @param node  The current node being visited in the traversal
	 * @param level The level to find the elements for
	 * @param nodes The list of elements at the specified level
	 */
	private void getLevelElementsHelper(BinaryTreeNode<E> node, int level, List<E> nodes) {
		if (node == null) {
			return;
		}

		if (level == 1) {
			nodes.add(node.getValue());
		}

		getLevelElementsHelper(node.getLeftChild(), level - 1, nodes);
		getLevelElementsHelper(node.getRightChild(), level - 1, nodes);
	}

	/**
	 * Checks whether the given node is a leaf node, i.e. it has no children.
	 * 
	 * @param node the node to check
	 * @return true if the node is a leaf node, false otherwise
	 */
	private boolean isLeaf(BinaryTreeNode<E> node) {
		return node.getLeftChild() == null && node.getRightChild() == null;
	}
}
