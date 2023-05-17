import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HeapPQWrapper {

	public static interface Heap<K, V> {
		public void add(K key, V value);

		public Entry<K, V> removeMin();

		public Entry<K, V> getMin();

		public void clear();

		public int size();

		public boolean isEmpty();

		public void print(int minWidth);
	}

	public static interface PriorityQueue<K, V> {
		public int size();

		public boolean isEmpty();

		public void insert(K key, V value) throws IllegalArgumentException;

		public Entry<K, V> min();

		public Entry<K, V> removeMin();
	}

	public static interface Entry<K, V> {
		public K getKey();

		public V getValue();
	}

	public static class HeapEntry<K, V> implements Entry<K, V> {
		private K key;
		private V value;

		public HeapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return this.key;
		}

		@Override
		public V getValue() {
			return this.value;
		}

		@Override
		public String toString() {
			return "(" + key + "," + value + ")";
		}

	}

	public static class EmptyHeapException extends RuntimeException {

		private static final long serialVersionUID = 1L;

		public EmptyHeapException(String s) {
			super(s);
		}

		public EmptyHeapException() {
			this("");
		}

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

	}

	public static class ArrayList<E> implements List<E> {

		// private fields
		private E elements[];
		private int currentSize;
		private static final int DEFAULT_SIZE = 15;

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

		@SuppressWarnings("unchecked")
		public ArrayList(int initialCapacity) {
			if (initialCapacity < 1)
				throw new IllegalArgumentException("Capacity must be at least 1.");
			this.currentSize = 0;
			this.elements = (E[]) new Object[initialCapacity];
		}

		public ArrayList() {
			this(DEFAULT_SIZE);
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
			for (int i = 0; i < this.size(); i++)
				newElements[i] = this.elements[i];
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
			if (obj == null)
				throw new IllegalArgumentException("Object cannot be null.");
			// first find obj in the array
			int position = this.firstIndex(obj);
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
		public int firstIndex(E obj) {
			for (int i = 0; i < this.size(); i++)
				if (this.elements[i].equals(obj))
					return i;
			return -1;
		}

		@Override
		public int lastIndex(E obj) {
			for (int i = this.size() - 1; i >= 0; i--)
				if (this.elements[i].equals(obj))
					return i;
			return -1;
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
			return this.firstIndex(obj) >= 0;
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

	}

	/**
	 * Implementation of the Min-Heap version of the Heap ADT using an ArrayList
	 * 
	 * @author Fernando J. Bermudez - bermed28
	 *
	 * @param <K>
	 * @param <V>
	 */
	public static class ListHeap<K, V> implements Heap<K, V> {
		/* TODO COPY OVER YOUR IMPLEMENTATION FROM EXERCISE 1 */

		/**
		 * TODO EXERCISE 1:
		 * 
		 * Complete the implementation of the Heap ADT
		 * using an ArrayList as discussed in lectures
		 */
		private List<Entry<K, V>> elements;
		private Comparator<K> comparator;

		/**
		 * Constructor to declare an empty Heap
		 * 
		 * @param initialCapacity
		 * @param elements
		 */
		public ListHeap(Comparator<K> comparator) {
			this.elements = new ArrayList<>();
			this.comparator = comparator;
		}

		/**
		 * Constructor to declare a heap with n elements using bottom-up heap
		 * construction
		 * 
		 * @param randomElements
		 */
		@SuppressWarnings("unchecked")
		public ListHeap(List<Entry<K, V>> randomElements) {
			this.comparator = (k1, k2) -> ((Comparable<K>) k1).compareTo(k2);
			bottomUpHeapConstruction(randomElements);
		}

		/**
		 * TODO EXERCISE 2:
		 * 
		 * Implement a method to construct a Heap from a given List of random entries.
		 * The point of this method is to construct it "bottom-up", start with (n + 1) /
		 * 2
		 * leaves, connect them, combine them into (n + 1) / 4 heaps, sift down the new
		 * roots,
		 * combine into (n + 1) / 8 heaps, sift down the new roots, and so on.
		 * 
		 * @param heap List of random entries to construct heap
		 * @return List of entries that meets the heap property so
		 *         it can be used as a min-heap
		 */
		private void bottomUpHeapConstruction(List<Entry<K, V>> heap) {
			/* TODO ADD YOUR CODE HERE */
			this.elements = heap;
			int startIndex = (size() - 2) / 2;
			for (int i = startIndex; i >= 0; i--) {
				downHeap(i);
			}
		}

		@Override
		public void add(K key, V value) {
			/* TODO ADD YOUR CODE HERE */
			int n = this.elements.size();
			this.elements.add(new MyEntry<>(key, value));
			upHeap(n);
		}

		@Override
		public Entry<K, V> removeMin() {
			/* TODO ADD YOUR CODE HERE */
			if (this.isEmpty())
				return null;
			Entry<K, V> min = this.elements.get(0);
			this.elements.set(0, this.elements.get(this.elements.size() - 1));
			this.elements.remove(this.elements.size() - 1);
			downHeap(0);
			return min;
		}

		@Override
		public Entry<K, V> getMin() {
			/* TODO ADD YOUR CODE HERE */
			if (this.isEmpty())
				return null;
			return this.elements.get(0);
		}

		@Override
		public void clear() {
			/* TODO ADD YOUR CODE HERE */
			this.elements.clear();
		}

		@Override
		public int size() {
			/* TODO ADD YOUR CODE HERE */
			return this.elements.size();
		}

		@Override
		public boolean isEmpty() {
			/* TODO ADD YOUR CODE HERE */
			return this.elements.isEmpty();
		}

		/**
		 * TODO EXERICSE 1:
		 * 
		 * Implement the upHeap method to use in the Heap ADT
		 * 
		 * @param heap Target Heap
		 * @param i    Index of the element to sift up
		 */
		private void upHeap(int i) {
			/* TODO ADD YOUR CODE HERE */
			int parent = (i - 1) / 2;
			if (i > 0
					&& this.comparator.compare(this.elements.get(i).getKey(), this.elements.get(parent).getKey()) < 0) {
				Entry<K, V> temp = this.elements.get(i);
				this.elements.set(i, this.elements.get(parent));
				this.elements.set(parent, temp);
				upHeap(parent);
			}
			return;
		}

		/**
		 * TODO EXERICSE 1:
		 * 
		 * Implement the downHeap method to use in the Heap ADT
		 * 
		 * @param heap Target Heap
		 * @param i    Index of the element to sift down
		 */
		private void downHeap(int i) {
			/* TODO ADD YOUR CODE HERE */
			int left = 2 * i + 1;
			int right = 2 * i + 2;
			int min = i;
			if (left < this.elements.size()
					&& this.comparator.compare(this.elements.get(left).getKey(), this.elements.get(min).getKey()) < 0) {
				min = left;
			}
			if (right < this.elements.size() && this.comparator.compare(this.elements.get(right).getKey(),
					this.elements.get(min).getKey()) < 0) {
				min = right;
			}
			if (min != i) {
				Entry<K, V> temp = this.elements.get(i);
				this.elements.set(i, this.elements.get(min));
				this.elements.set(min, temp);
				downHeap(min);
			}
			return;
		}

		@Override
		public String toString() {
			String s = "[";
			for (int i = 0; i < size() - 1; i++)
				s += elements.get(i).getKey() + ", ";

			s += elements.get(size() - 1).getKey() + "]";

			return s;
		}

		@Override
		public void print(int minWidth) {

			int size = size();

			int level = (int) (Math.log(size) / Math.log(2));
			int maxLength = (int) Math.pow(2, level) * minWidth;
			int currentLevel = -1;
			int width = maxLength;

			for (int i = 0; i < size; i++) {
				if ((int) (Math.log(i + 1) / Math.log(2)) > currentLevel) {
					currentLevel++;
					System.out.println();
					width = maxLength / (int) Math.pow(2, currentLevel);
				}
				System.out.print(center(String.valueOf(elements.get(i).getKey()), width));
			}
			System.out.println();
		}

		private String center(String text, int len) {
			String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
			float mid = (out.length() / 2);
			float start = mid - (len / 2);
			float end = start + len;
			return out.substring((int) start, (int) end);
		}

		/* ADD ANY AUXILIARY METHODS YOU WANT HERE */
		private static class MyEntry<K, V> implements Entry<K, V> {
			private K key;
			private V value;

			public MyEntry(K key, V value) {
				this.key = key;
				this.value = value;
			}

			public K getKey() {
				return key;
			}

			public V getValue() {
				return value;
			}

			public void setValue(V value) {
				this.value = value;
			}
		}
	}

	/**
	 * Priority Queue implementation using a Min-Heap
	 * 
	 * @author Fernando J. Bermudez - bermed28
	 *
	 * @param <K> Represents the priority of the value associated to it
	 * @param <V> Value inserted into Priority Queue
	 */
	public static class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {

		/**
		 * TODO EXERCISE 4:
		 * 
		 * Implement a Priority Queue using the ListHeap you made in exercise 4
		 * via object composition.
		 */
		private ListHeap<K, V> heap;

		public HeapPriorityQueue(Comparator<K> comparator) {
			/* TODO ADD YOUR CODE HERE */
			this.heap = new ListHeap<K, V>(comparator);
		}

		@Override
		public int size() {
			/* TODO ADD YOUR CODE HERE */
			return heap.size();
		}

		@Override
		public boolean isEmpty() {
			/* TODO ADD YOUR CODE HERE */
			return heap.size() == 0;
		}

		@Override
		public void insert(K key, V value) {
			/* TODO ADD YOUR CODE HERE */
			heap.add(key, value);
		}

		@Override
		public Entry<K, V> min() {
			/* TODO ADD YOUR CODE HERE */
			return heap.getMin();
		}

		@Override
		public Entry<K, V> removeMin() {
			/* TODO ADD YOUR CODE HERE */
			return heap.removeMin();
		}

		@Override
		public String toString() {
			return heap.toString();
		}
	}
}
