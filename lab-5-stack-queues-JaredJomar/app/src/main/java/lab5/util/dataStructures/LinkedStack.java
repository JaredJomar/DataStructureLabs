package lab5.util.dataStructures;

import java.util.EmptyStackException;

import lab5.util.interfaces.Stack;

/**
 * Implementation of the Stack ADT using a Singly Linked List Structure
 * 
 * @author Fernando J. Bermudez - bermed28
 *
 * @param <E>
 */
public class LinkedStack<E> implements Stack<E> {

	@SuppressWarnings("hiding")
	private class Node<E> {

		private E element;
		private Node<E> next;

		public Node(E elm, Node<E> next) {
			this.element = elm;
			this.next = next;
		}

		public Node(E elm) {
			this(elm, null);
		}

		public Node() {
			this(null, null);
		}

		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public void clear() {
			this.element = null;
			this.next = null;
		}
	} // End of Node Class

	private Node<E> header;
	private int currentSize;

	public LinkedStack() {
		this.header = new Node<>();
		this.currentSize = 0;
	}

	@Override
	public void push(E newEntry) {
		if (newEntry == null)
			throw new IllegalArgumentException("Parameter cannot be null");

		Node<E> newNode = new Node<>(newEntry, header.getNext());
		header.setNext(newNode);
		currentSize++;
	}

	@Override
	public E pop() {
		if (isEmpty())
			throw new EmptyStackException();
		E elm = header.getNext().getElement();
		Node<E> rmNode = header.getNext();
		header.setNext(rmNode.getNext());
		rmNode.clear();
		rmNode = null;
		currentSize--;
		return elm;
	}

	@Override
	public E top() {
		if (isEmpty())
			throw new EmptyStackException();

		return header.getNext().getElement();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public void clear() {
		while (!isEmpty())
			pop();
	}
}
