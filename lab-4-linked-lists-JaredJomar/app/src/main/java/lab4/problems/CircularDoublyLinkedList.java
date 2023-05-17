package lab4.problems;

import java.util.Iterator;
import java.util.NoSuchElementException;

import lab4.util.List;

public class CircularDoublyLinkedList<E> implements List<E> {

	private class Node {
		private E value;
		private Node next, prev;

		public Node(E value, Node next, Node prev) {
			this.value = value;
			this.next = next;
			this.prev = prev;
		}

		public Node(E value) {
			this(value, null, null); // Delegate to other constructor
		}

		public Node() {
			this(null, null, null); // Delegate to other constructor
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public void clear() {
			value = null;
			next = prev = null;
		}
	} // End of Node class

	private class ListIterator implements Iterator<E> {

		private Node nextNode;

		public ListIterator() {
			nextNode = header.getNext();
		}

		@Override
		public boolean hasNext() {
			return nextNode != trailer;
		}

		@Override
		public E next() {
			if (hasNext()) {
				E val = nextNode.getValue();
				nextNode = nextNode.getNext();
				return val;
			} else
				throw new NoSuchElementException();
		}

	} // End of ListIterator class, DO NOT REMOVE, TEST WILL FAIL

	/* private fields */
	private Node header, trailer; // "dummy" nodes
	private int currentSize;

	public CircularDoublyLinkedList() {
		/**
		 * Set dummy nodes to point to each other
		 * 
		 * --> header <--> trailer <--
		 * | |
		 * __________________________
		 * 
		 */
		header = new Node();
		trailer = new Node(null, header, header);
		header.setNext(trailer);
		header.setPrev(trailer);
		currentSize = 0;
	}

	@Override
	public void add(E obj) {
		/* TODO ADD YOUR CODE HERE */
		Node newNode = new Node(obj);
		newNode.setNext(trailer);
		newNode.setPrev(trailer.getPrev());
		trailer.getPrev().setNext(newNode);
		trailer.setPrev(newNode);
		currentSize++;
	}

	@Override
	public void add(E elm, int index) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}
		Node newNode = new Node(elm, currNode, currNode.getPrev());
		currNode.getPrev().setNext(newNode);
		currNode.setPrev(newNode);
		currentSize++;
	}

	@Override
	public boolean remove(E obj) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		while (currNode != trailer) {
			if (currNode.getValue().equals(obj)) {
				currNode.getPrev().setNext(currNode.getNext());
				currNode.getNext().setPrev(currNode.getPrev());
				currNode.clear();
				currentSize--;
				return true;
			}
			currNode = currNode.getNext();
		}
		return false;
	}

	@Override
	public boolean remove(int index) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}
		currNode.getPrev().setNext(currNode.getNext());
		currNode.getNext().setPrev(currNode.getPrev());
		currNode.clear();
		currentSize--;
		return true;
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		Node curNode = header;
		Node nextNode = curNode.getNext();

		while (nextNode != trailer) {
			if (nextNode.getValue().equals(obj)) {

				curNode.setNext(nextNode.getNext());
				nextNode.getNext().setPrev(curNode);

				nextNode.clear();
				currentSize--;
				counter++;

				nextNode = curNode.getNext();
			} else {
				curNode = nextNode;
				nextNode = nextNode.getNext();
			}
		}
		return counter;
	}

	@Override
	public E get(int index) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}
		return currNode.getValue();
	}

	@Override
	public E set(int index, E newElement) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		for (int i = 0; i < index; i++) {
			currNode = currNode.getNext();
		}
		E oldElement = currNode.getValue();
		currNode.setValue(newElement);
		return oldElement;
	}

	@Override
	public int firstIndexOf(E obj) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		int index = 0;
		while (currNode != trailer) {
			if (currNode.getValue().equals(obj)) {
				return index;
			}
			currNode = currNode.getNext();
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E obj) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = trailer.getPrev();
		int index = currentSize - 1;
		while (currNode != header) {
			if (currNode.getValue().equals(obj)) {
				return index;
			}
			currNode = currNode.getPrev();
			index--;
		}
		return -1;
	}

	@Override
	public E first() {
		/* TODO ADD YOUR CODE HERE */
		return header.getNext().getValue();
	}

	@Override
	public E last() {
		/* TODO ADD YOUR CODE HERE */
		return trailer.getPrev().getValue();

	}

	@Override
	public int size() {
		/* TODO ADD YOUR CODE HERE */
		return currentSize;
	}

	@Override
	public boolean isEmpty() {
		/* TODO ADD YOUR CODE HERE */
		return currentSize == 0;
	}

	@Override
	public boolean contains(E obj) {
		/* TODO ADD YOUR CODE HERE */
		Node currNode = header.getNext();
		while (currNode != trailer) {
			if (currNode.getValue().equals(obj)) {
				return true;
			}
			currNode = currNode.getNext();
		}
		return false;
	}

	@Override
	public void clear() {
		/* TODO ADD YOUR CODE HERE */
		header.setNext(trailer);
		trailer.setPrev(header);
		currentSize = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator();
	} // DO NOT DELETE, TESTS WILL FAIL

}