package lab6.RecLinkedList;

import java.util.Comparator;

public class RecursiveLinkedList<E> implements List<E> {
	private Node<E> head; // References first data node
	private int currentSize;

	@SuppressWarnings("unused")
	private static class Node<E> {
		private E value;
		private Node<E> next;

		public Node(E value, Node<E> next) {
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

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public void clear() {
			value = null;
			next = null;
		}
	} // End of Node class

	public RecursiveLinkedList() {
		head = null;
		currentSize = 0;
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
	public void clear() {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		if (this.size() == 1) {
			remove(0);
			return;
		}
		if (this.size() > 0) {
			recClear(head);
		} else {
			return;
		}
	}

	@Override
	public boolean contains(E e) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		Node<E> temp = head;
		return recContain(temp, e);
	}

	@Override
	public int removeAll(E e) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		return removeAllHelper(0, e);
	}

	@Override
	public E first() {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		return head.getValue();
	}

	@Override
	public E last() {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		return lastHelper(head, size() - 1);
	}

	@Override
	public int firstIndex(E e) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		if (head.getValue().equals(e)) {
			return 0;
		}
		if (!this.contains(e)) {
			return -1;
		}
		return firstIndexHelper(head.getNext(), e, 1);
	}

	@Override
	public int lastIndex(E e) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		if (!this.contains(e)) {
			return -1;
		}
		int firstIDX = firstIndex(e);
		return lastIndexHelper(firstIDX, firstIDX + 1); // Dummy Return
	}

	/* DO NOT MODIFY ANY OF THESE METHODS BELOW UNLESS IT HAS A TODO */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("RecursiveLinkedList.get: invalid index = " + index);
// index is valid
		return recGet(head, index);
	}

	@Override
	public void add(E e) {
		add(size(), e); // Add at the end of the list
	}

	@Override
	public void add(int index, E e) throws IndexOutOfBoundsException {
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException("RecursiveLinkedList.add: invalid index = " + index);
		if (index == 0) {
			Node<E> newNode = new Node(e, null);
			head = newNode;
			currentSize++;
			return;
		}
// index is valid for the add operation
		recAdd(head, index, e);
		currentSize++;
	}

	@Override
	public boolean remove(int index) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException("RecursiveLinkedList.add: invalid index = " + index);
		if (size() == 0) {
			return false;
		}
		if (index == 0) {
			Node<E> temp = head.getNext();
			head.clear();
			head = temp;
			currentSize--;
			return true;
		}
		recRemove(head, index);
		currentSize--;
		return true;
	}

	@Override
	public E set(int index, E e) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException("RecursiveLinkedList.set: invalid index = " + index);
// index is valid for set operation
		return recSet(head, index, e);
	}

	/* DO NOT REMOVE, JUNIT TESTER WILL FAIL */
	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray() {
		E[] theArray = (E[]) new Object[size()];
		int i = 0;
		for (Node<E> curNode = head; curNode != null; curNode = curNode.getNext())
			theArray[i++] = curNode.getValue();
		return theArray;
	}
	/* DO NOT MODIFY ANY OF THE METHODS ABOVE UNLESS IT HAS A TODO */
	/*******************************/

	/* Auxiliary recursive methods */
	/*******************************/
	/**
	 * Returns the value in the node corresponding to the index value i in the
	 * linked list whose first node is being referenced by f. On any such list the
	 * first node is the one associated to index 0, the second node is the one
	 * associated with index 1, and so on. It presumes that the list whose first
	 * node is f has at least i+1 nodes.
	 * 
	 * @param f First node of linked list to traverse
	 * @param i Index value of node whose value should be returned
	 * @return Value within node at index i
	 */
	private static <E> E recGet(Node<E> f, int i) {
		if (i == 0)
			return f.getValue();
		else
			return recGet(f.getNext(), i - 1);
	}

	/**
	 * TODO EXERCISE 1: Write the JavaDoc comments, including a formal verbal
	 * description (similar to what is done with recGet and recAdd), for the
	 * recRemove method.
	 * 
	 * Note: Remove the @SuppressWarnings annotation
	 *
	 * Removes the node corresponding to the index value i in the linked list whose
	 * first node is being referenced by f. On any such list the first node is the
	 * one associated to index 0, the second node is the one associated with index
	 * 1, and so on. It presumes that the list whose first node is f has at least
	 * i+1 nodes.
	 *
	 * This method returns the new first node of the modified linked list.
	 *
	 * @param f The first node of linked list to traverse
	 * @param i Index value of node to be removed
	 * @return The new first node of the modified linked list
	 */

	@SuppressWarnings("unused")
	private static <E> Node<E> recRemove(Node<E> f, int i) {
		if (i == 0) {
			Node<E> ntd = f;
			f = f.getNext();
			ntd.clear();
		} else
			f.setNext(recRemove(f.getNext(), i - 1));
		return f;
	}

	/**
	 * Inserts a new node in the linked list whose first node is being referenced by
	 * f so that the new node contains the data element e and it ends up occupying
	 * the position with index value i. Finally, it returns the reference to the
	 * first node of the list that results after the insertion is completed. It
	 * presumes that the list whose first node is f has at least i nodes.
	 * 
	 * @param f First node of linked list where node must be inserted
	 * @param i Index value of where new node must be inserted
	 * @param e Value or element that must be contained within the new node
	 */
	private static <E> Node<E> recAdd(Node<E> f, int i, E e) {
		if (i == 1) {
			if (f.getNext() == null) {
				Node<E> newNode = new Node(e, null);
				f.setNext(newNode);
			} else {
				Node<E> newNode = new Node(e, f.getNext());
				f.setNext(newNode);
			}
			return f;
		}
		return recAdd(f.getNext(), i - 1, e);
	}

	/**
	 * TODO EXERCISE 1: Write the JavaDoc comments, including a formal verbal
	 * description (similar to what is done with recGet and recAdd), for the recSet
	 * method. Sets the value of the node corresponding to the index value index in
	 * the linked list whose first node is being referenced by f to the value of e.
	 * On any such list the first node is the one associated to index 0, the second
	 * node is the one associated with index 1, and so on. It presumes that the list
	 * whose first node is f has at least index+1 nodes.
	 *
	 * This method returns the old value stored in the node that was modified.
	 *
	 * @param f     The first node of linked list to traverse
	 * @param index Index value of node whose value should be modified
	 * @param e     The new value to set
	 * @return The old value stored in the node that was modified
	 */
	private static <E> E recSet(Node<E> f, int index, E e) {
		if (index == 0) {
			E oldValue = f.getValue();
			f.setValue(e);
			return oldValue;
		}
		return recSet(f.getNext(), index - 1, e);
	}

	/************************************************************/
	/* Add as many auxiliary recursive methods as you want here */
	/************************************************************/
	public void recClear(Node<E> node) {
		if (node.getNext() == null) {
			node.clear();
			currentSize = 0;
			return;
		} else {
			Node<E> temp = node.getNext();
			node.clear();
			head = temp;
			currentSize--;
			recClear(head);
		}
	}

	public boolean recContain(Node<E> node, E e) {
		if (node.getValue().equals(e)) {
			return true;
		} else if (node.getNext() == null) {
			return false;
		} else {
			return recContain(node.getNext(), e);
		}
	}

	public int removeAllHelper(int count, E e) {
		if (firstIndex(e) != -1) {
			remove(firstIndex(e));
			return removeAllHelper(count + 1, e);
		} else {
			return count;
		}
	}

	public E lastHelper(Node<E> node, int countdown) {
		if (countdown == 0) {
			return node.getValue();
		} else {
			return lastHelper(node.getNext(), countdown - 1);
		}
	}

	public int firstIndexHelper(Node<E> node, E e, int idx) {
		if (node.getValue().equals(e)) {
			return idx;
		} else {
			return firstIndexHelper(node.getNext(), e, idx + 1);
		}
	}

	public int lastIndexHelper(int idx, int curPos) {
		if (get(idx).equals(get(curPos))) {
			if (curPos == size() - 1) {
				return curPos;
			}
			return lastIndexHelper(curPos, curPos + 1);
		}
		if (!get(idx).equals(get(curPos)) && curPos == size() - 1) {
			return idx;
		} else {
			return lastIndexHelper(idx, curPos + 1);
		}
	}

	/* TODO EXERCISE 3: ADD THE INSERTION SORT FUNCTIONALITY HERE */
	public void sort(Comparator<E> comparator) {
		if (head == null || head.getNext() == null) {
			return; // empty or one-element list is already sorted
		}
		Node<E> sorted = recInsertionSort(head.getNext(), comparator);
		head.setNext(sorted);
	}

	private Node<E> recInsertionSort(Node<E> node, Comparator<E> comparator) {
		if (node == null || node.getNext() == null) {
			return node; // empty or one-element list is already sorted
		}
		Node<E> next = node.getNext();
		node.setNext(null); // detach the current node from the rest of the list
		Node<E> sorted = recInsertionSort(next, comparator); // sort the rest of the list
		return merge(node, sorted, comparator); // merge the sorted rest of the list with the current node
	}

	private Node<E> merge(Node<E> left, Node<E> right, Comparator<E> comparator) {
		Node<E> result = null;
		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		if (comparator.compare(left.getValue(), right.getValue()) <= 0) {
			result = left;
			result.setNext(merge(left.getNext(), right, comparator));
		} else {
			result = right;
			result.setNext(merge(left, right.getNext(), comparator));
		}
		return result;
	}
}
