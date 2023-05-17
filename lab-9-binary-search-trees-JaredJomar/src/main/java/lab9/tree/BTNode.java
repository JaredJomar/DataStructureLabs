package lab9.tree;

/**
 * Class that will represent the nodes of the Tree.
 * Notice it differs from the TreeNode from the more general Tree and BinaryTree.
 * This is due to this one needing key-value pairs.
 * @author Fernando J. Bermudez - bermed28
 *
 * @param <K> - represents a key
 * @param <V> - represents the value pointed by the key
 */
public class BTNode<K, V> {
	
	private BTEntry<K, V> entry;
	private BTNode<K, V> parent;
	private BTNode<K, V> leftChild;
	private BTNode<K, V> rightChild;
	
	public BTNode(BTEntry<K, V> value, BTNode<K, V> parent, BTNode<K, V> leftChild, BTNode<K, V> rightChild) {
		this.entry = value;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public BTNode(K key, V value) {
		this(new BTEntry<>(key, value), null, null, null);
	}
	
	public BTEntry<K, V> getEntry() {
		return entry;
	}
	
	public void setEntry(BTEntry<K, V> entry) {
		this.entry = entry;
	}
	
	public BTNode<K, V> getParent() {
		return parent;
	}
	
	public void setParent(BTNode<K, V> parent) {
		this.parent = parent;
	}
	
	public BTNode<K, V> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(BTNode<K, V> leftChild) {
		this.leftChild = leftChild;
	}
	
	public BTNode<K, V> getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(BTNode<K, V> rightChild) {
		this.rightChild = rightChild;
	}
	
	public void clear() {
		this.entry.clear();
		this.parent = this.leftChild = this.rightChild = null;
	}
}
