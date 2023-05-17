package lab7.util.set;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import lab7.interfaces.Set;

/**
 * Implementation of the Set ADT using an Array-Based Structure
 * @author Fernando J. Bermudez - bermed28
 *
 * @param <E>
 */
public class ArraySet<E> implements Set<E> {

	private E[] elements;
	private int currentSize;
	private static final int DEFAULT_SIZE = 25;

	@SuppressWarnings("unchecked")
	public ArraySet(int initialCapacity) {
		if(initialCapacity < 1)
			throw new IllegalArgumentException("Capacity must be at least 1");

		elements = (E[]) new Object[initialCapacity];
		currentSize = 0;
	}

	public ArraySet() {
		this(DEFAULT_SIZE);
	}

	private class SetIterator implements Iterator<E> {

		private int currentPosition;

		public SetIterator() {
			currentPosition = 0;
		}

		@Override
		public boolean hasNext() {
			return currentPosition < size();
		}

		@Override
		public E next() {
			if(hasNext()) {
				E elm = elements[currentPosition++];
				return elm;
			}


			throw new NoSuchElementException();
		}

	}
	@Override
	public boolean add(E newEntry) {
		// TODO Auto-generated method stub
		if(newEntry == null)
			throw new IllegalArgumentException("Parameter cannot be null");

		if(size() == elements.length)
			reAllocate();
		if(!isMember(newEntry)) {
			elements[currentSize] = newEntry;
			currentSize++;
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	private void reAllocate() {
		E[] newElements = (E[]) new Object[elements.length * 2];
		for (int i = 0; i < size(); i++) {
			newElements[i] = elements[i];
		}

		newElements = elements;
	}

	@Override
	public boolean remove(E element) {
		for (int i = 0; i < size(); i++) {
			if(elements[i].equals(element)) {
				E elm = elements[i];
				elements[i] = elements[currentSize - 1];
				elements[currentSize - 1] = null;
				currentSize--;
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean isMember(E element) {
		for (int i = 0; i < size(); i++) 
			if(elements[i].equals(element))
				return true;

		return false;
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
		for (int i = 0; i < size(); i++) {
			elements[i] = null;
		}

		currentSize = 0;
	}

	@Override
	public Set<E> union(Set<E> S2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<E> intersection(Set<E> S2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<E> difference(Set<E> S2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isSubSet(Set<E> S2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		return new SetIterator();
	}
	
	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
}
