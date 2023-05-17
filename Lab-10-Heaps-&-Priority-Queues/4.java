import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;


public class CPUWrapper {
	
	public static interface Heap<K, V> {
		public void add(K key, V value);
		public Entry<K, V> removeMin();
		public Entry<K, V> getMin();
		public void clear();
		public int size();
		public boolean isEmpty();
		public void print(int minWidth);
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
				}
				else
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
			E newElements[] = (E[]) new Object[2*this.elements.length];
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
				}
				else
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
			}
			else
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
			}
			else
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
	 * @author Fernando J. Bermudez - bermed28
	 *
	 * @param <K>
	 * @param <V>
	 */
	public abstract class ListHeap<K, V> implements Heap<K, V> {

		/**
		 * TODO EXERCISE 1:
		 * 
		 * Complete the implementation of the Heap ADT using an ArrayList as discussed
		 * in lectures
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
		 * 2 leaves, connect them, combine them into (n + 1) / 4 heaps, sift down the
		 * new roots, combine into (n + 1) / 8 heaps, sift down the new roots, and so
		 * on.
		 * 
		 * @param heap List of random entries to construct heap
		 * @return List of entries that meets the heap property so it can be used as a
		 *         min-heap
		 */
		private void bottomUpHeapConstruction(List<Entry<K, V>> heap) {
			this.elements = heap;
			for (int i = elements.size() / 2 - 1; i >= 0; i--) {
				downHeap(i);
			}
		}

		@Override
		public void add(K key, V value) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).getKey().equals(key)) {
				Entry<K, V> existingEntry = elements.get(i);
				Entry<K, V> updatedEntry = new HeapEntry<K, V>(key, value);
				elements.set(i, updatedEntry);
				if (comparator.compare(updatedEntry.getKey(), existingEntry.getKey()) < 0) {
					upHeap(i);
				} else {
					downHeap(i);
				}
				return;
			}
		}

		Entry<K, V> entry = new HeapEntry<K, V>(key, value);
		elements.add(entry);
		upHeap(elements.size() - 1);
		}

		@Override
		public Entry<K, V> removeMin() {
			Entry<K, V> last = elements.get(elements.size() - 1);
			elements.remove(elements.size() - 1);
			if (!elements.isEmpty()) {
				elements.set(0, last);
				downHeap(0);
			}
			return elements.get(0);
		}

		@Override
		public Entry<K, V> getMin() {
			if (isEmpty()) {
				return null;
			} else {
				return elements.get(0);
			}
		}

		@Override
		public void clear() {
			elements = null;
		}

		@Override
		public int size() {
			return elements.size();
		}

		@Override
		public boolean isEmpty() {
			if (elements.size() == 0) {
				return true;
			}
			return false;
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
			Entry<K, V> current = elements.get(i);
			while (i > 0) {
				int parentIndex = (i - 1) / 2;
				Entry<K, V> parent = elements.get(parentIndex);
				if (comparator.compare(current.getKey(), parent.getKey()) >= 0) {
					break;
				}
				elements.set(i, parent);
				i = parentIndex;
			}
			elements.set(i, current);
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
			while (true) {
				int smallest = i;
				int leftheapchild = 2 * i + 1;
				int rightheapchild = leftheapchild + 1;

				if (leftheapchild < elements.size() && comparator.compare(elements.get(leftheapchild).getKey(),
						elements.get(smallest).getKey()) < 0) {
					smallest = leftheapchild;
				}

				if (rightheapchild < elements.size() && comparator.compare(elements.get(rightheapchild).getKey(),
						elements.get(smallest).getKey()) < 0) {
					smallest = rightheapchild;
				}

				if (smallest != i) {
					// Swap the current element with its smallest child
					Entry<K, V> temp = elements.get(smallest);
					elements.set(smallest, elements.get(i));
					elements.set(i, temp);
					i = smallest;
				} else {
					break;
				}
			}
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

	}

	
	/**
	 * TODO EXERCISE 5:
	 * 
	 * You are given n processes labeled from 0 to n - 1 represented by a 2D integer 
	 * array processes, where processes[i] = [enqueueTime_i, executionTime_i] means 
	 * that the ith process will be available to process at enqueueTime_i and will 
	 * take executionTime_i to finish executing.
	 * 
	 * You have a single-threaded CPU that can process at most one task at a time 
	 * and will act in the following way:
	 * 
	 * 1) If the CPU is idle and there are no available tasks to process, the CPU remains idle.
	 * 2) If the CPU is idle and there are available tasks, the CPU will choose 
	 * 	  the one with the shortest processing time. 
	 * 3) If multiple tasks have the same shortest processing time, it will choose 
	 *    the task with the smallest index.
	 * 4) Once a task is started, the CPU will process the entire task without stopping.
	 * 5) The CPU can finish a task then start a new one instantly.
	 * 
	 * Return the order in which the CPU will process the tasks.
	 * 
	 * Hint: Sort the processes by enqueue time and then use a Heap/PQ to place them in order
	 * of smallest processing time (since we implemented a Min-Heap, this idea works!)
	 * 
	 * @param processes		Processes to execute by CPU
	 * @return				The order in which the CPU will process the tasks.
	 */
	public static int[] getProcessOrder(int[][] processes) {
        PriorityQueue<int[]> nextTask = new PriorityQueue<int[]>((a, b) -> (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));
        
        int sortedTasks[][] = new int[processes.length][3];
        for (int i = 0; i < processes.length; ++i) {
            sortedTasks[i][0] = processes[i][0];
            sortedTasks[i][1] = processes[i][1];
            sortedTasks[i][2] = i;
        }
        
        Arrays.sort(sortedTasks, (a, b) -> Integer.compare(a[0], b[0]));
        int tasksProcessingOrder[] = new int[processes.length];
        
        long currTime = 0;
        int taskIndex = 0;
        int ansIndex = 0;
        
        while (taskIndex < processes.length || !nextTask.isEmpty()) {
            if (nextTask.isEmpty() && currTime < sortedTasks[taskIndex][0]) {
                currTime = sortedTasks[taskIndex][0];
            }
            
            while (taskIndex < processes.length && currTime >= sortedTasks[taskIndex][0]) { 
                nextTask.add(sortedTasks[taskIndex]);
                ++taskIndex;
            }
            
            int processTime = nextTask.peek()[1];
            int index = nextTask.peek()[2];
            nextTask.remove();
            
            currTime += processTime; 
            tasksProcessingOrder[ansIndex++] = index;
        }
        
        return tasksProcessingOrder;
		}
}