package lab3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lab03P4Wrapper {

	/* TODO ADD YOUR FUNCTIONAL INTERFACES HERE */

	interface FirstIndexLambda<E> {
		boolean firstIndex(E e);
	}

	interface LastIndexLambda<E> {
		public boolean lastIndex(E e);
	}

	interface LambdaFilter<E> {
		public boolean filter(E e);
	}

	interface LambdaMap<E> {
		public E map(E e);
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

		public int size();

		public boolean isEmpty();

		public boolean contains(E obj);

		public void clear();
		/* TODO ADD THE SPECIFIED ADT METHOD SIGNATURES HERE */

		public int firstIndex(FirstIndexLambda<E> l);

		public int lastIndex(LastIndexLambda<E> l);

		public List<E> filter(LambdaFilter<E> l);

		public List<E> map(LambdaMap<E> l);
	}

	public static class ArrayList<E> implements List<E> {

		// private fields
		private E elements[];

		private int currentSize;

		private static final int DEFAULT_SIZE = 10;

		private class ListIterator implements Iterator<E> {
			private int currentPosition;

			public ListIterator() {
				this.currentPosition = 0;
			}

			@Override
			public boolean hasNext() {
				return this.currentPosition < size();
			}

			@Override
			public E next() {
				if (this.hasNext()) {
					return (E) elements[this.currentPosition++];
				} else
					throw new NoSuchElementException();
			}
		}

		public ArrayList() {
			this(DEFAULT_SIZE);
		}

		@SuppressWarnings("unchecked")
		public ArrayList(int initialCapacity) {
			if (initialCapacity < 1)
				throw new IllegalArgumentException("Capacity must be at least 1.");
			this.currentSize = 0;
			this.elements = (E[]) new Object[initialCapacity];
		}

		@Override
		public void add(E obj) {
			if (obj == null)
				throw new IllegalArgumentException("Object cannot be null.");
			else {
				if (this.size() == this.elements.length)
					reAllocate();
				this.elements[this.currentSize++] = obj;
			}
		}

		@SuppressWarnings("unchecked")
		private void reAllocate() {
			// create a new array with twice the size
			E newElements[] = (E[]) new Object[2 * this.elements.length];
			for (int i = 0; i < this.size(); i++) {
				newElements[i] = this.elements[i];
			}
			// replace old elements with newElements
			this.elements = newElements;
		}

		@Override
		public void add(int index, E obj) {
			if (obj == null)
				throw new IllegalArgumentException("Object cannot be null.");
			if (index == this.currentSize)
				this.add(obj); // Use other method to "append"
			else {
				if (index >= 0 && index < this.currentSize) {
					if (this.currentSize == this.elements.length)
						reAllocate();
					// move everybody one spot to the back
					for (int i = this.currentSize; i > index; i--)
						this.elements[i] = this.elements[i - 1];
					// add element at position index
					this.elements[index] = obj;
					this.currentSize++;
				} else
					throw new ArrayIndexOutOfBoundsException();
			}
		}

		@Override
		public boolean remove(E obj) {
			// TODO UNCOMMENT ONCE FINISHED IMPLEMENTATION
			if (obj == null)
				throw new IllegalArgumentException("Object cannot be null.");
			// first find obj in the array
			int position = this.firstIndex((x) -> x.equals(obj));
			if (position >= 0) // found it
				return this.remove(position);
			else

				return false;
		}

		@Override
		public boolean remove(int index) {
			if (index >= 0 && index < this.currentSize) {
				// move everybody one spot to the front
				for (int i = index; i < this.currentSize - 1; i++)
					this.elements[i] = this.elements[i + 1];
				this.elements[--this.currentSize] = null;
				return true;
			} else
				return false;
		}

		@Override
		public int removeAll(E obj) {
			int counter = 0;
			while (this.remove(obj))
				counter++;
			return counter;
		}

		@Override
		public E get(int index) {
			if (index >= 0 && index < this.size())
				return this.elements[index];
			else
				throw new ArrayIndexOutOfBoundsException();
		}

		@Override
		public E set(int index, E obj) {
			if (obj == null)
				throw new IllegalArgumentException("Object cannot be null.");
			if (index >= 0 && index < this.size()) {
				E temp = this.elements[index];
				this.elements[index] = obj;
				return temp;
			} else
				throw new ArrayIndexOutOfBoundsException();
		}

		@Override
		public E first() {
			if (this.isEmpty())
				return null;
			else
				return this.elements[0];
		}

		@Override
		public E last() {
			if (this.isEmpty())
				return null;
			else
				return this.elements[this.currentSize - 1];
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
		public boolean contains(E obj) {
			// TODO UNCOMMENT ONCE FINISHED IMPLEMENTATION
			if (this.firstIndex(x -> x.equals(obj)) >= 0)
				return true;

			return false;
		}

		@Override
		public void clear() {
			for (int i = 0; i < this.currentSize; i++)
				this.elements[i] = null;
			this.currentSize = 0;
		}

		@Override
		public Iterator<E> iterator() {
			return new ListIterator();
		}

		/* TODO ADD THE SPECIFIED ADT METHOD IMPLEMENTATIONS HERE */

		public int firstIndex(FirstIndexLambda<E> l) {
			for (int i = 0; i < currentSize; i++) {
				if (l.firstIndex(elements[i])) {
					return i;
				}
			}
			return -1;
		}

		public int lastIndex(LastIndexLambda<E> l) {
			for (int i = currentSize - 1; i >= 0; i--) {
				if (l.lastIndex(elements[i])) {
					return i;
				}
			}
			return -1;
		}

		public List<E> filter(LambdaFilter<E> l) {
			List<E> result = new ArrayList<>();
			for (int i = 0; i < currentSize; i++) {
				E element = elements[i];
				if (l.filter(element)) {
					result.add(element);
				}
			}
			return result;
		}

		public List<E> map(LambdaMap<E> l) {
			List<E> result = new ArrayList<>();
			for (int i = 0; i < currentSize; i++) {
				E element = elements[i];
				E mapped = l.map(element);
				result.add(mapped);
			}
			return result;
		}
	}

}