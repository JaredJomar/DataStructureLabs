package lab8.tree;

import lab8.interfaces.TreeNode;

public class BinaryTreeNode<E> implements TreeNode<E> {

	private E value;
	private BinaryTreeNode<E> leftChild;
	private BinaryTreeNode<E> rightChild;
	private BinaryTreeNode<E> parent;

	public BinaryTreeNode(E value, BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
		this.value = value;
		this.parent = parent;
		this.leftChild = left;
		this.rightChild = right;
	}
	
	public BinaryTreeNode(E value) {
		this(value, null, null, null);
	}
	
	@Override
	public E getValue() {
		return this.value;
	}
	
	public void setValue(E value) {
		this.value = value;
	}

	public BinaryTreeNode<E> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(BinaryTreeNode<E> leftChild) {
		this.leftChild = leftChild;
	}

	public BinaryTreeNode<E> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode<E> rightChild) {
		this.rightChild = rightChild;
	}

	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}
}
