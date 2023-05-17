/**
 * You are given a positive integer X in which you can only perform 3
 * operations. You can only subtract one from X, divide X by 2, or divide X by
 * 3. Write a method called convertXToOne() that finds the least amount of
 * operations to convert the number X to 1 by only performing these 3
 * operations.
 * What should happen when X is not a multiple of 2 or 3?
 * Be careful with Java's division, it can convert an integer division to a
 * floating-point one and compare a float instead of an int (1.03 == 1 will
 * return true if Java converts 1.03 to 1 for example)
 * Implementations that use a different approach to avoid using a Graph
 * algorithm will receive zero credit
 * Example:
 * A call to convertXToOne(9) returns 2 since you can divide 9 by 3 twice to get
 * to 1. For visual purposes, here is a graph representing the operations you
 * can do to get to 1 from 9 as a starting point. Notice that each node in the
 * graph represents the current state of X, and each edge represents a
 * particular operation performed on the current state of X
 * (Image)
 */

import java.io.PrintStream;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lab11P1Wrapper {

	public static interface Queue<E> {
		public int size();
		public boolean isEmpty();
		public E front();
		public E dequeue();
		public void enqueue(E e);
		public void makeEmpty();
		public void print(PrintStream P);
	}
	public static interface List<E> extends Iterable<E> {

		public void add(E obj);
		public void add(int index, E obj);
		public boolean remove(E obj);
		public boolean remove(int index);
		public int removeAll(E obj);
		public E get(int index);
		public E set(int index, E obj);
		public E first();
		public E last();
		public int firstIndex(E obj);
		public int lastIndex(E obj);
		public int size();
		public boolean isEmpty();
		public boolean contains(E obj);
		public void clear();
		public void printList();
	}
	
	public static class LinkedList<E> implements List<E> {

		private class Node {
			private E value;
			private Node next;

			public Node(E value, Node next) {
				this.value = value;
				this.next = next;
			}

			public Node(E value) {
				this(value, null); // Delegate to other constructor
			}

			public Node() {
				this(null, null); // Delegate to other constructor
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

			public void clear() {
				value = null;
				next = null;
			}				
		} // End of Node class


		private class ListIterator implements Iterator<E> {

			private Node nextNode;

			public ListIterator() {
				nextNode = header.getNext();
			}

			@Override
			public boolean hasNext() {
				return nextNode != null;
			}

			@Override
			public E next() {
				if (hasNext()) {
					E val = nextNode.getValue();
					nextNode = nextNode.getNext();
					return val;
				}
				else
					throw new NoSuchElementException();				
			}

		} // End of ListIterator class


		// private fields
		private Node header;	
		private int currentSize;


		public LinkedList() {
			header = new Node();
			currentSize = 0;
		}

		@Override
		public Iterator<E> iterator() {
			return new ListIterator();
		}

		@Override
		public void add(E obj) {
			Node curNode, newNode;
			// Need to find the last node
			for (curNode = header; curNode.getNext() != null; curNode = curNode.getNext());
			// Now curNode is the last node
			// Create a new Node and make curNode point to it
			newNode = new Node(obj);
			curNode.setNext(newNode);
			currentSize++;
		}

		@Override
		public void add(int index, E obj) {
			Node curNode, newNode;

			// First confirm index is a valid position
			// We allow for index == size() and delegate to add(object).
			if (index < 0 || index > size())
				throw new IndexOutOfBoundsException();
			if (index == size())
				add(obj); // Use our "append" method
			else {
				// Get predecessor node (at position index - 1)
				curNode = get_node(index - 1);
				// The new node must be inserted between curNode and curNode's next
				// Note that if index = 0, curNode will be header node
				newNode = new Node(obj, curNode.getNext());
				curNode.setNext(newNode);
				currentSize++;
			}
		}

		@Override
		public boolean remove(E obj) {
			Node curNode = header;
			Node nextNode = curNode.getNext();

			// Traverse the list until we find the element or we reach the end
			while (nextNode != null && !nextNode.getValue().equals(obj)) {
				curNode = nextNode;
				nextNode = nextNode.getNext();
			}

			// Need to check if we found it
			if (nextNode != null) { // Found it!
				// If we have A -> B -> C and want to remove B, make A point to C 
				curNode.setNext(nextNode.getNext());
				nextNode.clear(); // free up resources
				currentSize--;
				return true;
			}
			else
				return false;
		}

		@Override
		public boolean remove(int index) {
			Node curNode, rmNode;
			// First confirm index is a valid position
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException();
			curNode = get_node(index - 1);
			rmNode = curNode.getNext();
			// If we have A -> B -> C and want to remove B, make A point to C 
			curNode.setNext(rmNode.getNext());
			rmNode.clear();
			currentSize--;		

			return true;
		}

		/* Private method to return the node at position index */
		private Node get_node(int index) {
			Node curNode;

			/* First confirm index is a valid position
			   Allow -1 so that header node may be returned */
			if (index < -1 || index >= size())
				throw new IndexOutOfBoundsException();
			curNode = header;
			// Since first node is pos 0, let header be position -1
			for (int curPos = -1; curPos < index; curPos++)
				curNode = curNode.getNext();
			return curNode;
		}

		@Override
		public int removeAll(E obj) {
			int counter = 0;
			Node curNode = header;
			Node nextNode = curNode.getNext();

			/* We used the following in ArrayList, and it would also work here,
			 * but it would have running time of O(n^2).
			 * 
			 * while (remove(obj))
			 * 		counter++;
			 */

			// Traverse the entire list
			while (nextNode != null) { 
				if (nextNode.getValue().equals(obj)) { // Remove it!
					curNode.setNext(nextNode.getNext());
					nextNode.clear();
					currentSize--;
					counter++;
					/* Node that was pointed to by nextNode no longer exists
					   so reset it such that it's still the node after curNode */
					nextNode = curNode.getNext();
				}
				else {
					curNode = nextNode;
					nextNode = nextNode.getNext();
				}
			}
			return counter;
		}

		@Override
		public E get(int index) {
			// get_node allows for index to be -1, but we don't want get to allow that
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException();
			return get_node(index).getValue();
		}

		@Override
		public E set(int index, E obj) {
			// get_node allows for index to be -1, but we don't want set to allow that
			if (index < 0 || index >= size())
				throw new IndexOutOfBoundsException();
			Node theNode = get_node(index);
			E theValue = theNode.getValue();
			theNode.setValue(obj);
			return theValue;
		}

		@Override
		public E first() {
			return get(0);
		}

		@Override
		public E last() {
			return get(size()-1);
		}

		@Override
		public int firstIndex(E obj) {
			Node curNode = header.getNext();
			int curPos = 0;
			// Traverse the list until we find the element or we reach the end
			while (curNode != null && !curNode.getValue().equals(obj)) {
				curPos++;
				curNode = curNode.getNext();
			}
			if (curNode != null)
				return curPos;
			else
				return -1;
		}

		@Override
		public int lastIndex(E obj) {
			int curPos = 0, lastPos = -1;
			// Traverse the list 
			for (Node curNode = header.getNext(); curNode != null; curNode = curNode.getNext()) {
				if (curNode.getValue().equals(obj))
					lastPos = curPos;
				curPos++;
			}
			return lastPos;
		}

		@Override
		public int size() {
			return currentSize;
		}

		@Override
		public boolean isEmpty() {
			return size() == 0;
		}

		@Override
		public boolean contains(E obj) {
			return firstIndex(obj) != -1;
		}

		@Override
		public void clear() {
			// Avoid throwing an exception if the list is already empty
			while (size() > 0)
				remove(0);
		}

		public void reverse() {

			//x->1->2->3->4->x
			Node prevNode = null;
			Node curNode = header.getNext();
			Node nextNode = null;
			while(curNode != null) {
				nextNode = curNode.getNext();
				curNode.setNext(prevNode);
				prevNode = curNode;
				curNode = nextNode;
			}

			header.next = prevNode;

		}

		@Override
		public void printList() {
			int i = 1;
			System.out.print("{");
			for (E e : this) {
				if(i == size())
					System.out.print(e + "}\n");
				else
					System.out.print(e + ", \n");
				i++;
			}
		}

	}

	public static interface Pair<T, E> {
		public T getFirst();
		public E getSecond();
		public boolean equals(Pair<T,E> p);
	}

	public static class OrderedPair<T, E> implements Pair<T, E> {

		private T first;
		private E second;

		public OrderedPair(T first, E second) {
			super();
			this.first = first;
			this.second = second;
		}

		@Override
		public T getFirst() {
			return this.first;
		}

		@Override
		public E getSecond() {
			return this.second;
		}

		@Override
		public boolean equals(Pair<T, E> p) {
			return this.getFirst().equals(p.getFirst()) &&
					this.getSecond().equals(p.getSecond());
		}

		@Override
		public String toString() {
			return "(" + this.first + "," + this.second + ")";
		}

	}

	public static class DoublyLinkedQueue<E> implements Queue<E> {

		private static class Node<E>{
			private E element;
			private Node<E> next;
			private Node<E> prev;

			public Node() {
				this.element = null;
				this.next = this.prev = null;

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

		}

		private Node<E> header;
		private Node<E> tail;
		private int currentSize;

		public DoublyLinkedQueue() {
			this.currentSize = 0;
			this.header = new Node<>();
			this.tail = new Node<>();

			this.header.setNext(this.tail);
			this.tail.setPrev(this.header);
		}

		@Override
		public int size() {
			return this.currentSize;
		}

		@Override
		public boolean isEmpty() {
			return this.size() == 0;
		}

		@Override
		public E front() {
			return (this.isEmpty() ? null : this.header.getNext().getElement());
		}

		@Override
		public E dequeue() {
			if (this.isEmpty()) {
				return null;
			}
			else {
				Node<E> target = null;
				target = this.header.getNext();
				E result = target.getElement();
				this.header.setNext(target.getNext());
				target.getNext().setPrev(this.header);
				target.setNext(null);
				target.setPrev(null);
				target.setElement(null);
				this.currentSize--;
				return result;
			}
		}

		@Override
		public void enqueue(E e) {
			Node<E> newNode = new Node<E>();
			newNode.setElement(e);
			newNode.setNext(this.tail);
			newNode.setPrev(this.tail.getPrev());
			this.tail.setPrev(newNode);
			newNode.getPrev().setNext(newNode);
			this.currentSize++;
		}

		@Override
		public void makeEmpty() {
			while (this.dequeue() != null);

		}

		@Override
		public void print(PrintStream P) {
			Node<E> temp = this.header.getNext();
			while (temp != this.tail) {
				P.println(temp.getElement());
				temp = temp.getNext();
			}

		}

	}

	/**
	 * You are given a positive integer X in which you can only perform 3 operations.
	 * You can only subtract one from X, divide X by 2, or divide X by 3.
	 * Design an algorithm that finds the least amount of operations needed 
	 * to convert the number X to 1 by only performing these 3 operations.
	 * 
	 * NOTE: What should happen when X is not a multiple of 2 or 3?
	 * 		
	 * @param X		Input Number
	 * @return 		The number of operations performed to convert X to 1
	 */
	public static int convertXToOne(int X) {
		/*ADD YOUR CODE HERE*/
		int[] dp = new int[X+1];
		dp[0] = 0;
		dp[1] = 0;
		for(int i = 2; i <= X; i++) {
			dp[i] = dp[i-1] + 1;
			if(i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			}
			if(i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
			}
		}
		return dp[X];
	}
}