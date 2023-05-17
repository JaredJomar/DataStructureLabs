package lab9.tree;

import java.util.Comparator;

import lab9.interfaces.Entry;
import lab9.interfaces.List;
import lab9.interfaces.Map;
import lab9.util.list.LinkedList;

/**
 * Implementation of the Map ADT using a Binary Search Tree Structure
 * 
 * @author Fernando J. Bermudez - bermed28
 *
 * @param <K> Keys of each Key-Value pair of BST
 * @param <V> Values of each Key-Value pair of BST
 */
public class TreeMap<K, V> implements Map<K, V> {

	/**
	 * TODO EXERCISE 2:
	 * 
	 * Java has a version of the Map ADT that is built
	 * with a Binary Search Tree called TreeMap.
	 * 
	 * Finish completing the implementation of the Map ADT using the
	 * Binary search Tree Logic, WITHOUT using Object Composition
	 * 
	 * Hint: Isn't this the same as just re-using the logic
	 * from a Binary Search Tree that uses a linked structure?
	 * The way to implement this was discussed in lectures.
	 * 
	 * Note: The testers for this exercise (TreeMapTester.java)
	 * that check if clear(), size() & isEmpty() will NOT work
	 * if the remove() method is not implemented correctly.
	 */
	private BTNode<K, V> root;
	private int currentSize;
	private Comparator<K> comparator;

	public TreeMap(Comparator<K> comparator) {
		this.root = null;
		this.comparator = comparator;
		this.currentSize = 0;
	}

	@SuppressWarnings("unchecked")
	public TreeMap() {
		this((k1, k2) -> ((Comparable<K>) k1).compareTo(k2));
	}

	@Override
	public V get(K key) {
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null");
		
		BTNode<K, V> current = root;
		while (current != null) {
			int compareResult = comparator.compare(key, current.getEntry().getKey());
			if (compareResult == 0) {
				return current.getEntry().getValue();
			} else if (compareResult < 0) {
				current = current.getLeftChild();
			} else {
				current = current.getRightChild();
			}
		}
		
		return null;
	}
	

	@Override
	public void put(K key, V value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("Parameters cannot be null.");
		/* TODO ADD YOUR CODE HERE */

		BTEntry<K, V> entry = new BTEntry<K, V>(key, value);
		if (root == null) {
			root = new BTNode<K, V>(entry, null, null, null);
			currentSize++;
		} else {
			BTNode<K, V> parent = null;
			BTNode<K, V> current = root;
			while (current != null) {
				int compareResult = comparator.compare(key, current.getEntry().getKey());
				if (compareResult == 0) {
					current.setEntry(entry);
					return;
				} else if (compareResult < 0) {
					parent = current;
					current = current.getLeftChild();
				} else {
					parent = current;
					current = current.getRightChild();
				}
			}

			BTNode<K, V> newNode = new BTNode<K, V>(entry, parent, null, null);
			if (comparator.compare(key, parent.getEntry().getKey()) < 0) {
				parent.setLeftChild(newNode);
			} else {
				parent.setRightChild(newNode);
			}
			currentSize++;
		}
	}

	/**
	 * TODO EXERCISE 1:
	 * 
	 * Removes node that has the given key. If key not found returns null.
	 * 
	 * If the node is found then HOW the node is removed is dependent of how many
	 * children the node has.
	 * 1. Node is a leaf. It's an easy remove.
	 * 2. Node has 1 child. The child replaces the node being removed by becoming
	 * the new child of the node's parent.
	 * 3. Node has both children. Hardest case. The node being removed is replaced
	 * by its in-order predecessor.
	 * 
	 * NOTE: Also consider what would happen with cases when BST only has the root
	 * or has 2 nodes and we remove the root.
	 * Hint: Much like get() and add() you will need an auxiliary function.
	 * 
	 * Note: The testers for this exercise (TreeMapTester2.java) will NOT work fully
	 * if the other ADT methods are not implemented fully
	 * 
	 * @param key - Key of the Entry we want to remove.
	 * @return Returns the Entry that was removed or null if not found.
	 */
	@Override
	public V remove(K key) {
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null.");

		BTNode<K, V> node = findNode(key);

		if (node == null) {
			return null;
		}
		if (root == node) {
			V rootValue = root.getEntry().getValue();
			if (node.getLeftChild() == null && node.getRightChild() == null) {
				root = null;
			} else {
				if (node.getLeftChild() == null || node.getRightChild() == null) {
					remove(node);
				} else {
					BTNode<K, V> Predecessor = inorderPredecessor(node);
					node.setEntry(Predecessor.getEntry());
					remove(Predecessor);
				}
			}
			currentSize--;
			return rootValue;
		}
		V oldValue = node.getEntry().getValue();

		if (node.getLeftChild() == null || node.getRightChild() == null) {
			remove(node);
		} else {
			BTNode<K, V> successor = inorderSuccessor(node);
			node.setEntry(successor.getEntry());
			remove(successor);
		}
		currentSize--;
		return oldValue;
	}

	private void remove(BTNode<K, V> node) {
		BTNode<K, V> parent = node.getParent();

		if (node.getLeftChild() == null && node.getRightChild() == null) {
			if (parent == null) {
				root = null;
			} else if (parent.getLeftChild() == node) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);
			}
		} else if (node.getLeftChild() != null) {
			BTNode<K, V> child = node.getLeftChild();
			child.setParent(parent);

			if (parent == null) {
				root = child;
			} else if (parent.getLeftChild() == node) {
				parent.setLeftChild(child);
			} else {
				parent.setRightChild(child);
			}
		} else {
			BTNode<K, V> child = node.getRightChild();
			child.setParent(parent);

			if (parent == null) {
				root = child;
			} else if (parent.getLeftChild() == node) {
				parent.setLeftChild(child);
			} else {
				parent.setRightChild(child);
			}
		}
	}

	private BTNode<K, V> findNode(K key) {
		return findNode(root, key);
	}

	private BTNode<K, V> findNode(BTNode<K, V> node, K key) {
		if (node == null) {
			return null;
		}

		int cmp = comparator.compare(key, node.getEntry().getKey());
		if (cmp == 0) {
			return node;
		} else if (cmp < 0) {
			return findNode(node.getLeftChild(), key);
		} else {
			return findNode(node.getRightChild(), key);
		}
	}

	private BTNode<K, V> inorderSuccessor(BTNode<K, V> node) {
		if (node == null) {
			return null;
		}
		BTNode<K, V> current = node.getRightChild();
		while (current != null && current.getLeftChild() != null) {
			current = current.getLeftChild();
		}
		return current;
	}

	private BTNode<K, V> inorderPredecessor(BTNode<K, V> node) {
		if (node == null) {
			return null;
		}
		BTNode<K, V> pred = node.getLeftChild();
		while (pred != null && pred.getRightChild() != null) {
			pred = pred.getRightChild();
		}
		return pred;
	}

	@Override
	public boolean containsKey(K key) {
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null.");
		/* TODO ADD YOUR CODE HERE */
		for (K k : getKeys()) {
			if (k.equals(key)) {
				return true;
			}
		}
		return false; // Dummy Return
	}

	@Override
	public List<K> getKeys() {
		/* TODO ADD YOUR CODE HERE */
		List<K> keys = new LinkedList<K>();
		getKeysHelper(root, keys);
		return keys;
	}

	private void getKeysHelper(BTNode<K, V> node, List<K> keys) {
		if (node != null) {
			getKeysHelper(node.getLeftChild(), keys);
			keys.add(node.getEntry().getKey());
			getKeysHelper(node.getRightChild(), keys);
		}
	}

	@Override
	public List<V> getValues() {
		/* TODO ADD YOUR CODE HERE */
		List<V> values = new LinkedList<V>();
		getValuesHelper(root, values);
		return values;
	}

	private void getValuesHelper(BTNode<K, V> node, List<V> values) {
		if (node != null) {
			getValuesHelper(node.getLeftChild(), values);
			values.add(node.getEntry().getValue());
			getValuesHelper(node.getRightChild(), values);
		}
	}

	@Override
	public int size() {
		/* TODO ADD YOUR CODE HERE */
		if (root == null) {
			return 0;
		}
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		/* TODO ADD YOUR CODE HERE */
		if (root == null) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		/* TODO ADD YOUR CODE HERE */
		if (root != null) {
			root = null;
			currentSize = 0;
		}
	}

	/* DO NOT EDIT, TESTS WILL FAIL IF REMOVED/MODIFIED */
	@Override
	public BTNode<K, V> getRoot() {
		return this.root;
	}

	public void print() {
		this.printAux(this.root, 0);
	}

	private void printAux(BTNode<K, V> N, int i) {
		if (N != null) {
			this.printAux(N.getRightChild(), i + 8);
			System.out.println();
			for (int j = 0; j < i; ++j) {

				System.out.print(" ");
			}
			System.out.println(N.getEntry().getValue());
			this.printAux(N.getLeftChild(), i + 8);
		}

	}
}
