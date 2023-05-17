package lab4.problems;

public class HasPrefixSumProblem {

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

		public void setData(E data) {
			this.data = data;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public E getData() {
			return data;
		}

		public Node<E> getNext() {
			return next;
		}
	}

	/**
	 * TODO EXERCISE 5:
	 * Implement a method that determines if a Linked List
	 * has an initial sequence of nodes whose values sum to n.
	 * 
	 * If so, it returns an integer corresponding to how many elements at the
	 * beginning of the list add up to n.
	 * 
	 * The method receives as parameter a Node that represents the head node of a
	 * Singly Linked List,
	 * as well as a integer n denoting a target sum to search for.
	 * 
	 * It is assumed that the list always has at least one node.
	 * 
	 * If no sequence of initial elements adds up to n, the method will
	 * return a negative value, which is specified as follows.
	 * 1. The negative of the size of the list if the sum
	 * of all elements in the list is less than n.
	 * 2. The negative of the minimum number of elements at the
	 * beginning of the list whose sum exceeds n.
	 * 
	 * Examples:
	 * 1) {1,2,3,4,5}, n = 10 -> returns 4
	 * 2) {2,4,6,8,10}, n = 10 -> returns -3 (since 2 + 4 + 6 = 12, which is larger
	 * than n = 10)
	 * 3) {1,2,3,4}, n = 15 -> returns -4 (since 1 + 2 + 3 + 4 = 10, which is
	 * smaller than n = 15)
	 * 
	 * All the elements in the list are assumed to be non-negative integers.
	 * 
	 * @param first - Head Node of Singly Linked List
	 * @param n     - Integer denoting target sum
	 * @return Number of elements needed to sum up to n
	 */
	public int hasPrefixSum(Node<Integer> first, int n) {
		/* TODO ADD YOUR CODE HERE */
		int sum = 0;
		int count = 0;
		Node<Integer> curr = first;
		while (curr != null) {
			sum += curr.getData();
			count++;
			if (sum == n) {
				return count;
			} else if (sum > n) {
				return -count;
			}
			curr = curr.getNext();
		}
		return -count;
	}
}