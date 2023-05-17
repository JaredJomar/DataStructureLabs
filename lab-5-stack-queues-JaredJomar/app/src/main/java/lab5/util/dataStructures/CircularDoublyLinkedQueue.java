package lab5.util.dataStructures;

import lab5.util.EmptyQueueException;
import lab5.util.interfaces.Deque;

/**
 * Implementation of Deque ADT using a Circular Doubly-Linked Queue
 * 
 * @author Fernando J. Bermudez
 * @param <E>
 */
public class CircularDoublyLinkedQueue<E> implements Deque<E> {

	/**
	 * TODO EXERCISE 5:
	 * 
	 * A Deque (pronounced "deck") is short for Double Ended Queue.
	 * This means that we can insert, remove and inspect elements from the front and
	 * rear of the queue.
	 * 
	 * The special thing about this ADT is that it can be implemented in many ways
	 * such as with an array, a doubly-linked list, and so on.
	 * 
	 * One implementation we will study is the one with a Circular Doubly Linked
	 * List.
	 * Using the provided Deque interface, implement the Deque ADT using a
	 * Circular Doubly Linked List-type structure with ONLY A DUMMY HEADER (NO DUMMY
	 * TRAILER).
	 * 
	 * Note: Throw an EmptyQueueException when Queue is empty and you try to access
	 * an element from the queue.
	 */

	@SuppressWarnings("hiding")
	private class Node<E> {
		private E element;
		private Node<E> next, prev;

		public Node(E elm, Node<E> next, Node<E> prev) {
			this.element = elm;
			this.next = next;
			this.prev = prev;
		}

		public Node(E elm, Node<E> next) {
			this(elm, next, null);
		}

		public Node(E elm) {
			this(elm, null, null);
		}

		public Node() {
			this(null, null, null);
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

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public void clear() {
			next = null;
			prev = null;
			element = null;
		}
	} // End of Node Class

	private Node<E> header; // Dummy node referencing beginning and end of circular queue
	private int currentSize;

	public CircularDoublyLinkedQueue() {
		// Make header point to itself when Queue is empty
		header = new Node<>(null, header, header);
		currentSize = 0;
	}

	@Override
	public void addFirst(E elm) {
		/* TODO ADD YOUR CODE HERE */
		Node<E> newNode = new Node<>(elm);
		if (isEmpty()) {
			header.setNext(newNode);
			header.setPrev(newNode);
			newNode.setNext(header);
			newNode.setPrev(header);
		} else {
			newNode.setNext(header.getNext());
			newNode.setPrev(header);
			header.getNext().setPrev(newNode);
			header.setNext(newNode);
		}
		currentSize++;
	}

	@Override
	public void addLast(E elm) {
		/* TODO ADD YOUR CODE HERE */
		Node<E> newNode = new Node<>(elm);
		if (isEmpty()) {
			header.setNext(newNode);
			header.setPrev(newNode);
			newNode.setNext(header);
			newNode.setPrev(header);
		} else {
			newNode.setNext(header);
			newNode.setPrev(header.getPrev());
			header.getPrev().setNext(newNode);
			header.setPrev(newNode);
		}
		currentSize++;
	}

	@Override
	public E removeFirst() {
		/* TODO ADD YOUR CODE HERE */
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty");
		}
		Node<E> firstNode = header.getNext();
		E firstElement = firstNode.getElement();
		if (currentSize == 1) {
			header.setNext(header);
			header.setPrev(header);
		} else {
			header.setNext(firstNode.getNext());
			firstNode.getNext().setPrev(header);
		}
		firstNode.clear();
		currentSize--;
		return firstElement;
	}

	@Override
	public E removeLast() {
		/* TODO ADD YOUR CODE HERE */
		if (isEmpty()) {
			throw new EmptyQueueException("Queue is empty");
		}
		Node<E> lastNode = header.getPrev();
		E lastElement = lastNode.getElement();
		if (currentSize == 1) {
			header.setNext(header);
			header.setPrev(header);
		} else {
			header.setPrev(lastNode.getPrev());
			lastNode.getPrev().setNext(header);
		}
		lastNode.clear();
		currentSize--;
		return lastElement;
	}

	@Override
	public E getFirst() {
		/* TODO ADD YOUR CODE HERE */
		return header.getNext().getElement();
	}

	@Override
	public E getLast() {
		/* TODO ADD YOUR CODE HERE */
		return header.getPrev().getElement();
	}

	@Override
	public boolean removeFirstOccurrence(E elm) {
		/* TODO ADD YOUR CODE HERE */
		Node<E> curNode = header.getNext();
		while (curNode != header) {
			if (curNode.getElement().equals(elm)) {
				curNode.getPrev().setNext(curNode.getNext());
				curNode.getNext().setPrev(curNode.getPrev());
				currentSize--;
				return true;
			}
			curNode = curNode.getNext();
		}
		return false;
	}

	@Override
	public boolean removeLastOccurrence(E elm) {
		/* TODO ADD YOUR CODE HERE */
		Node<E> curNode = header.getPrev();
		while (curNode != header) {
			if (curNode.getElement().equals(elm)) {
				curNode.getPrev().setNext(curNode.getNext());
				curNode.getNext().setPrev(curNode.getPrev());
				currentSize--;
				return true;
			}
			curNode = curNode.getPrev();
		}
		return false;
	}

	@Override
	public int size() {
		/* TODO ADD YOUR CODE HERE */
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		/* TODO ADD YOUR CODE HERE */
		return (size() == 0);
	}

	/**
	 * DO NOT USE THIS IN EXERCISE AND
	 * DO NOT DELETE, TESTS WILL FAILS
	 */
	@Override
	public String[] toArray() {
		String[] arr = new String[size()];

		Node<E> curNode = header.getNext();
		for (int i = 0; curNode != header; curNode = curNode.getNext(), i++) {
			arr[i] = (String) curNode.getElement();
		}
		return arr;
	}
}