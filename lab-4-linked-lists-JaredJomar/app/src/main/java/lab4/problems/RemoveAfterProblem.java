package lab4.problems;

public class RemoveAfterProblem {

	private static int countClears = 0;
	private static int countSetNext = 0;

	public static class Node<E> {

		private E data;
		private Node<E> next;

		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}

		public Node(E data) {
			this(data, null);
		}

		public E getData() {
			return data;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			countSetNext++; // for grading purposes
			this.next = next;
		}

		public void clear() {
			countClears++; // for grading purposes
			data = null;
			next = null;
		}
	}

	public int getCountClears() {
		return countClears;
	}

	public int getCountSetNext() {
		return countSetNext;
	}

	public void setCountClears(int count) {
		countClears = count;
	}

	public void setCountSetNext(int count) {
		countSetNext = count;
	}

	/**
	 * TODO EXERCISE 4:
	 * 
	 * Implement a method that removes from the list all elements, if any,
	 * that are immediately after one occurrence of a given
	 * element and which are not equal to the given element.
	 * 
	 * The method receives as a parameter a Node that represents the head of a
	 * linked list,
	 * as well as a target which will be used to find nodes containing that element
	 * such that
	 * we can remove the subsequent nodes that one (if they subsequent node does not
	 * contain an
	 * element that is equal to target).
	 * 
	 * For example, assume that the specified element is 5.
	 * 
	 * 1. If the list is (4, 5, 5, 6, 8, 5, 9, 5), then
	 * the list remaining would be (4, 5, 5, 8, 5, 5).
	 * The method would return value 2 (number of removals).
	 * 
	 * 2. If the list is (4, 3, 6, 7, 8), the the list is
	 * not altered because it has no occurrence of 5. And
	 * the method would return 0.
	 * 
	 * Your solution must be as efficient as possible and must
	 * properly apply the clear() method (of class Node) to each
	 * deleted node.
	 * 
	 * @param <E>
	 * @param first  - Head Node of the Linked List
	 * @param target - Target element in a node to find that will
	 *               remove nodes after it that are not equal to target
	 * @return The number of copies the method removed
	 */
	public <E> int removeAfter(Node<E> first, E target) {
		/* TODO ADD YOUR CODE HERE */
		int testRemovals = 0;
		Node<E> currentNode = first;
		while (currentNode != null) {
			if (currentNode.getData().equals(target)) {
				Node<E> nextNode = currentNode.getNext();
				if (nextNode != null) {
					if (!nextNode.getData().equals(target)) {
						currentNode.setNext(nextNode.getNext());
						nextNode.clear();
						testRemovals++;
					}
				}
			}
			currentNode = currentNode.getNext();
		}
		return testRemovals;
	}
}