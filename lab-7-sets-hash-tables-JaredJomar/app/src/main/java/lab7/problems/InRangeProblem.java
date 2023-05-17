package lab7.problems;

import java.util.Comparator;

import lab7.interfaces.Set;
import lab7.util.set.ArraySet;

public class InRangeProblem<E> {
	
	Set<E> set;
	private Comparator<E> comparator;
	
	public InRangeProblem(Set<E> set, Comparator<E> comparator) {
		this.set = set;
		this.comparator = comparator;
	}
	
	public Set<E> getSet() {
		return set;
	}

	public void setSet(Set<E> s) {
		set = s;
	}

	/**
	 * TODO EXERCISE 5:
	 * Implement a member method inRange() for the Set ADT. 
	 * 
	 * Given a set S, this method returns a new set with all elements 
	 * that lie inside the range [e1, e2] for this set.
	 *  
	 * The method receives the values e1, e2, and a comparator C for type E.
	 *  
	 * For example, if C is a comparator object, and if S = {8, 16, 1, 100, 0, 10}, 
	 * a call to S.rangeIn(0, 10, C) returns resultSet = {8, 1, 0, 10}.
	 * 
	 * Note: You do not need to implement the comparator, it will be a private field in the class.
	 * 
	 * @param e1	First element of range
	 * @param e2	Last element of range
	 * @param C 	Comparator to compare elements between e1 and e2
	 * @return		Set of elements that have values in between e1 and e2.
	 */
	public Set<E> inRange(E e1, E e2) {
		/* TODO EXERCISE 4: ADD YOUR CODE HERE */
		Set<E> result = new ArraySet<>();

		for (E element : set) {
			if (comparator.compare(element, e1) >= 0 && comparator.compare(element, e2) <= 0) {
				result.add(element);
			}
		}

		return result;
	}
}