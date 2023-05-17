package lab5.util.dataStructures;

import lab5.util.EmptyQueueException;
import lab5.util.interfaces.Queue;

/**
 * Implementation of the Queue ADT using a Singly Linked Structure
 * 
 * @param <E>
 */
public class LinkedQueue<E> implements Queue<E> {

	/**
	 * TODO EXERCISE 4:
	 * 
	 * Implement the Queue ADT using a Singly Linked List-type structure WITHOUT
	 * DUMMY NODES.
	 * In this case, the head node refers to the first data node and the tail node
	 * refers to the last data node.
	 * If the list is empty, then both the head and the tail must be null.
	 * 
	 * Note: Because there are no dummy nodes, you must be especially careful with
	 * the edge cases and test accordingly.
	 * For example, when enqueueing in an empty queue, when dequeueing the last
	 * element, etc.
	 * 
	 * Think: Is it more convenient to have the tail node represent the front of the
	 * queue or the rear of the queue?
	 * How are the worst-case running times of enqueue and dequeue affected by this
	 * decision?
	 */

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

	private Node<E> head, tail; // References to beginning and end of queue
	private int currentSize;

	public LinkedQueue() {
		this.head = tail = null;
		this.currentSize = 0;
	}

	@Override
	public void enqueue(E newEntry) {
		/* TODO ADD YOUR CODE HERE */
		Node<E> newNode = new Node<>(newEntry);
		if (isEmpty()) {
			head = newNode;
		} else {
			tail.setNext(newNode);
		}
		tail = newNode;
		currentSize++;
	}

	@Override
	public E dequeue() {
		/* TODO ADD YOUR CODE HERE */
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			E temp = head.getElement();
			head = head.getNext();
			currentSize--;
			return temp;
		}
	}

	@Override
	public E front() {
		/* TODO ADD YOUR CODE HERE */
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return head.getElement();
		}
	}

	@Override
	public boolean isEmpty() {
		/* TODO ADD YOUR CODE HERE */
		return (size() == 0);
	}

	@Override
	public int size() {
		/* TODO ADD YOUR CODE HERE */
		return this.currentSize;
	}

	@Override
	public void clear() {
		/* TODO ADD YOUR CODE HERE */
		head = tail = null;
		currentSize = 0;
	}
}
