/**
 * You are given the name of a starting city A, the name of an ending city B
 * (that are both located on a map) to visit. Each City has a toll cost that is
 * needed to visit the adjacent City on your road trip. You are also given a
 * Graph<V, E> that has a method called getNeighbors() that returns the adjacent
 * City instances with their respective toll costs passed as a parameter.
 * Implement a method called canTravelMinCost() that returns the minimum toll
 * cost if you were to make a road trip from A to B.
 * Carefully study the classes used for this problem (Graph<V, E>, City, etc.)
 * as well as the provided data structures in the wrapper code. You may use
 * these to help out to be able to tackle this in a proper manner.
 * Hint: A good idea to start approaching this problem is to first do a
 * sub-problem. Try to find a path from A to B such that you do not have to pay
 * any tolls. Once that sub-problem is made, modify it such that it allows to
 * pay tolls, but reducing it to the minimum.
 * Hint: Use a PriorityQueue or a Heap, where the total cost is the priority (or
 * alternatively, you can use a sorted list, sorted in ascending order)
 * For visual purposes, here is the map used for testing to help you
 * (Image)
 */

 import java.util.Comparator;
 import java.util.Iterator;
 import java.util.NoSuchElementException;
 // No tengo ni idea si podemos usar otros imports pero me funciona el codigo con ellos
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
 
 public class RoadTripWrapper {
	 
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
		 
		 public ArrayList(E[] elements) {
			 this();
			 for (E e : elements) {
				 add(e);
			 }
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
	 public static class ListHeap<K, V> implements Heap<K, V> {
 
		 private List<Entry<K, V>> elements;
		 private Comparator<K> comparator;
		 
		 public ListHeap(Comparator<K> comparator) {
			 this.elements = new ArrayList<>();
			 this.comparator = comparator;
		 }
 
		 @Override
		 public void add(K key, V value) {
			 if(key == null || value == null)
				 throw new IllegalArgumentException("Parameters cannot be null");
			 this.elements.add(new HeapEntry<>(key, value));
			 upHeap(size() - 1);
		 }
 
		 @Override
		 public Entry<K, V> removeMin() {
			 Entry<K, V> result = getMin();
			 elements.set(0, elements.get(size() - 1));
			 elements.remove(size() - 1);
			 downHeap(0);
			 return result; 
		 }
 
		 @Override
		 public Entry<K, V> getMin() {
			 if(isEmpty())
				 throw new EmptyHeapException();
			 return elements.get(0);
		 }
 
		 @Override
		 public void clear() {
			 while(!isEmpty())
				 removeMin();
		 }
 
		 @Override
		 public int size() {
			 return elements.size();
		 }
 
		 @Override
		 public boolean isEmpty() {
			 return size() == 0;
		 }
		 
		 private void upHeap(int i) {
			 int parent; 
			 while (i > 0) {
				 parent = this.parent(i);
				 if (comparator.compare(elements.get(i).getKey(), elements.get(parent).getKey()) >= 0) {
					 break;
				 }
				 else {
					 Entry<K, V> temp = elements.get(i);
					 elements.set(i, elements.get(parent));
					 elements.set(parent, temp);
 
					 i = parent;
				 }
			 }
		 }
		 
		 private void downHeap(int i) {
			 int leftIndex, rightIndex, smallestIndex;
			 
			 while (leftChild(i) < elements.size()) {
				 
				 leftIndex = leftChild(i);
				 smallestIndex = leftIndex;
				 
				 if (rightChild(i) < elements.size()) {
					 
					 rightIndex = rightChild(i);
					 
					 K key1 = elements.get(leftIndex).getKey();
					 K key2 = elements.get(rightIndex).getKey();
					 
					 if (comparator.compare(key1, key2) > 0) 
						 smallestIndex = rightIndex;
					 
				 }
				 
				 if (comparator.compare(elements.get(smallestIndex).getKey(), elements.get(i).getKey()) >= 0)
					 break;
				 else {
					 Entry<K,V> temp = elements.get(i);
					 elements.set(i, elements.get(smallestIndex));
					 elements.set(smallestIndex, temp);
					 i = smallestIndex;
				 }
			 }
		 }
		 
		 @Override
		 public String toString() {
			 String s = "[";
			 for(int i = 0; i < size() - 1; i++) 
				 s += elements.get(i).getKey() + ", ";
			 
			 s += elements.get(size() - 1).getKey() + "]";
			 
			 return s;
		 }
		 
		 @Override
		 public void print(int minWidth) {
 
			 int size = size();
 
			 int level = (int) (Math.log(size) / Math.log(2));
			 int maxLength = (int) Math.pow(2, level) * minWidth;
			 int currentLevel = -1 ;
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
		 
		 private String center(String text, int len){
			 String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
			 float mid = (out.length()/2);
			 float start = mid - (len/2);
			 float end = start + len; 
			 return out.substring((int)start, (int)end);
		 }
		 
		 /* ADD ANY AUXILIARY METHODS YOU WANT HERE */
		 private int leftChild(int n) {
			 return 2 * n + 1;
		 }
 
		 private int rightChild(int n) {
			 return 2 * n + 2;
		 }
 
		 private int parent(int n) {
			 return (n - 1) / 2;
		 } 
 
	 }
	 
	 public static interface PriorityQueue<K, V> { 
		 public int size(); 
		 public boolean isEmpty(); 
		 public void insert(K key, V value) throws IllegalArgumentException; 
		 public Entry<K, V> min(); 
		 public Entry<K, V> removeMin(); 
	 }
	 
	 /**
	  * Priority Queue implementation using a Min-Heap
	  * @author Fernando J. Bermudez - bermed28
	  *
	  * @param <K>	Represents the priority of the value associated to it
	  * @param <V>	Value inserted into Priority Queue
	  */
	 public static class HeapPriorityQueue<K, V> implements PriorityQueue<K, V> {
		 
		 /**
		  * TODO EXERCISE 4:
		  * 
		  * Implement a Priority Queue using the ListHeap you made in exercise 4
		  * via object composition.
		  */
		 
		 private Heap<K, V> heap;
		 
		 public HeapPriorityQueue(Comparator<K> comparator) {
			 heap = new ListHeap<>(comparator);
		 }	
		 @SuppressWarnings("unchecked")
		 public HeapPriorityQueue() {
			 this((k1, k2) -> ((Comparable<K>) k1).compareTo(k2));
		 }
 
		 @Override
		 public int size() {
			 return heap.size();
		 }
 
		 @Override
		 public boolean isEmpty() {
			 return heap.isEmpty();
		 }
 
		 @Override
		 public void insert(K key, V value) {
			 heap.add(key, value);
		 }
 
		 @Override
		 public Entry<K, V> min() {
			 return heap.getMin();
		 }
 
		 @Override
		 public Entry<K, V> removeMin() {
			 return heap.removeMin();
		 }
		 
		 @Override
		 public String toString() {
			 return heap.toString();
		 }
	 }
 
	 
	 public interface Map<K, V> {
		 public V get(K key);
		 public void put(K key, V value);
		 public V remove(K key);
		 public boolean containsKey(K key);
		 public List<K> getKeys();
		 public List<V> getValues();
		 public int size();
		 public boolean isEmpty();
		 public void clear();
 
	 }
	 
	 /**
	  * Implementation of the Map ADT using a Binary Search Tree Structure
	  * @author Fernando J. Bermudez - bermed28
	  *
	  * @param <K>	Keys of each Key-Value pair of BST
	  * @param <V>	Values of each Key-Value pair of BST
	  */
	 public static class TreeMap<K extends Comparable<? super K>, V> implements Map<K, V> {
		 
 
		 @SuppressWarnings("hiding")
		 private class BTEntry<K, V> implements Entry<K, V> {
 
			 private K key;
			 private V value;
 
			 public BTEntry(K key, V value) {
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
 
			 public void setKey(K key) {
				 this.key = key;
			 }
 
			 public void setValue(V value) {
				 this.value = value;
			 }
			 
			 public void clear() {
				 this.key = null;
				 this.value = null;
			 }
		 }
		 
		 @SuppressWarnings("hiding")
		 private class BTNode<K, V> {
			 
			 private BTEntry<K, V> entry;
			 private BTNode<K, V> parent;
			 private BTNode<K, V> leftChild;
			 private BTNode<K, V> rightChild;
			 
			 public BTNode(BTEntry<K, V> value, BTNode<K, V> parent, BTNode<K, V> leftChild, BTNode<K, V> rightChild) {
				 this.entry = value;
				 this.parent = parent;
				 this.leftChild = leftChild;
				 this.rightChild = rightChild;
			 }
			 
			 public BTNode(K key, V value) {
				 this(new BTEntry<>(key, value), null, null, null);
			 }
			 
			 public BTEntry<K, V> getEntry() {
				 return entry;
			 }
			 
			 public void setEntry(BTEntry<K, V> entry) {
				 this.entry = entry;
			 }
			 
			 public BTNode<K, V> getParent() {
				 return parent;
			 }
			 
			 public void setParent(BTNode<K, V> parent) {
				 this.parent = parent;
			 }
			 
			 public BTNode<K, V> getLeftChild() {
				 return leftChild;
			 }
			 
			 public void setLeftChild(BTNode<K, V> leftChild) {
				 this.leftChild = leftChild;
			 }
			 
			 public BTNode<K, V> getRightChild() {
				 return rightChild;
			 }
			 
			 public void setRightChild(BTNode<K, V> rightChild) {
				 this.rightChild = rightChild;
			 }
			 
			 public void clear() {
				 this.entry.clear();
				 this.parent = this.leftChild = this.rightChild = null;
			 }
		 }
 
		 private BTNode<K, V> root;
		 private int currentSize;
		 private Comparator<K> comparator;
		 
		 public TreeMap(Comparator<K> comparator) {
			 this.root = null;
			 this.comparator = comparator;
			 this.currentSize = 0;
		 }
		 
		 public TreeMap() {
			 this((k1, k2) -> k1.compareTo(k2));
		 }
 
		 @Override
		 public V get(K key) {
			 if(key == null)
				 throw new IllegalArgumentException("Key cannot be null");
			 return recGet(root, key);
		 }
		 
		 private V recGet(BTNode<K, V> root, K key) {
			 if (root == null) return null;
			 else {
				 int comparison = comparator.compare(key, root.getEntry().getKey());
				 if (comparison == 0)
					 return root.getEntry().getValue();
				 else if (comparison < 0)
					 return recGet(root.getLeftChild(), key);
				 else
					 return recGet(root.getRightChild(), key);
				 
			 }
			 
		 }
		 
		 @Override
		 public void put(K key, V value) {
			 if(key == null || value == null)
				 throw new IllegalArgumentException("Parameters cannot be null.");
			 
			 BTEntry<K, V> newEntry = new BTEntry<>(key, value);
			 
			 if(isEmpty()) {
				 BTNode<K, V> newNode = new BTNode<>(newEntry, null, null, null);
				 this.root = newNode;
				 currentSize++;
			 }
			 else recPut(root, newEntry);
		 }
		 
		 private void recPut(BTNode<K, V> root, BTEntry<K, V> newEntry) {
			 int comparison = comparator.compare(newEntry.getKey(), root.getEntry().getKey());
			 
			 if(comparison == 0) 
				 return;
			 else {
				 if(comparison < 0) {
					 if(root.getLeftChild() == null) {
						 BTNode<K, V> newNode = new BTNode<>(newEntry, root, null, null);
						 root.setLeftChild(newNode);
						 currentSize++;
					 } else recPut(root.getLeftChild(), newEntry);
				 } else {
					 if(root.getRightChild() == null) {
						 BTNode<K, V> newNode = new BTNode<>(newEntry, root, null, null);
						 root.setRightChild(newNode);
						 currentSize++;
					 } else recPut(root.getRightChild(), newEntry);
				 }
			 }
		 }
 
		 @Override
		 public V remove(K key) {
			 if (key == null)
				 throw new IllegalArgumentException("Key cannot be null.");
			 if (isEmpty())
				 return null;
			 return recRemove(key, root);
		 }
		 
		 private V recRemove(K key, BTNode<K, V> N) {
			 if (N == null)
				 return null;
			 int comparison = comparator.compare(key, N.getEntry().getKey());
			 if (comparison == 0) { // Found it!
				 V v = N.getEntry().getValue();
				 removeNode(N);
				 currentSize--;
				 return v;
			 }
			 else if (comparison < 0)
				 return recRemove(key, N.getLeftChild());
			 else
				 return recRemove(key, N.getRightChild());
		 }
		 
		 private void removeNode(BTNode<K, V> N) {
			 BTNode<K, V> left  = N.getLeftChild();
			 BTNode<K, V> right = N.getRightChild();
 
			 if (left != null && right != null) { // N has two children
				 BTNode<K, V> predecessor = left;
				 while (predecessor.getRightChild() != null) // Keep descending to the right
					 predecessor = predecessor.getRightChild();
				 N.getEntry().setKey(predecessor.getEntry().getKey());
				 N.getEntry().setValue(predecessor.getEntry().getValue());
				 removeNode(predecessor);
			 }
			 else { 
				 BTNode<K, V> ntk;
				 if (left == null)
					 ntk = right;
				 else
					 ntk = left;
				 if (N == root) { 
					 root = ntk;
				 }
				 else {
					 
					 BTNode<K, V> parent = N.getParent();
					 if (parent.getLeftChild() == N)
						 parent.setLeftChild(ntk);
					 else
						 parent.setRightChild(ntk);
					 if (ntk != null)
						 ntk.setParent(parent);
				 }
				 /* We've bypassed N, so we can now clear its contents */
				 N.clear();
			 }
			 
		 }
		 
		 
 
		 @Override
		 public boolean containsKey(K key) {
			 return get(key) != null;
		 }
 
		 @Override
		 public List<K> getKeys() {
			 List<K> keys = new ArrayList<>();
			 recGetKeys(root, keys);
			 return keys;
		 }
 
		 private void recGetKeys(BTNode<K, V> root, List<K> keys) {
			 if(root == null) return;
			 else {
				 recGetKeys(root.getLeftChild(), keys);
				 keys.add(root.getEntry().getKey());
				 recGetKeys(root.getRightChild(), keys);
			 }
		 }
 
		 @Override
		 public List<V> getValues() {
			 List<V> values = new ArrayList<>();
			 recGetValues(root, values);
			 return values;
		 }
 
		 private void recGetValues(BTNode<K, V> root, List<V> values) {
			 if(root == null) return;
			 else {
				 recGetValues(root.getLeftChild(), values);
				 values.add(root.getEntry().getValue());
				 recGetValues(root.getRightChild(), values);
			 }
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
			 /* TODO ADD YOUR CODE HERE*/
			 while(!isEmpty())
				 remove(root.getEntry().getKey());
		 }
	 }
 
	 public static class City {
		 private String cityName;
		 private int tollCost;
		 
		 public City(String cityName, int tollCost) {
			 this.cityName = cityName;
			 this.tollCost = tollCost;
		 }
 
		 public String getCityName() {
			 return cityName;
		 }
 
		 public void setCityName(String cityName) {
			 this.cityName = cityName;
		 }
 
		 public int getTollCost() {
			 return tollCost;
		 }
 
		 public void setTollCost(int tollCost) {
			 this.tollCost = tollCost;
		 }
 
		 @Override
		 public boolean equals(Object obj) {
			 City c2 = (City) obj;
			 return getCityName().equals(c2.getCityName()) && getTollCost() == c2.getTollCost();
		 }
		 
		 
	 }
	 
	 /**
	  * Simple Graph Implementation using Adjacency Lists
	  * 
	  * @author Fernando J. Bermudez - bermed28
	  * @param <V>
	  * @param <E>
	  */
	 public static class Graph<V, E> {
		 
		 private Map<V, List<E>> adjacencyList;
		 
		 public Graph(Map<V, List<E>> adjacencyList) {
			 this.adjacencyList = adjacencyList;
		 }
		 
		 public void add(V node, List<E> edges) {
			 adjacencyList.put(node, edges);
		 }
		 
		 public List<E> getNeighbors(V node) {
			 if(!adjacencyList.containsKey(node))
				 throw new NoSuchElementException(node + " is not in graph.");
			 return adjacencyList.get(node);
		 }
		 
	 }	
	 
	 /**
	  * You are given a starting city A, an ending city B that are in a map,
	  * to visit. Each city has a toll cost that is needed to visit the adjacent city
	  * in your road trip. You are also given a Graph that has a method that returns
	  * the adjacent cities with their respective toll costs passed as parameter. 
	  * 
	  * Implement a method that returns the minimum toll cost if you were to make a 
	  * road trip from A to B.
	  * 
	  * Hint: Use a Priority Queue (or a Heap), where the total cost is the priority (or you 
	  * can use a sorted list, sorted in ascending order)
	  * 
	  * @param A					Starting City
	  * @param B					Ending City
	  * @param G					Graph of map
	  * @return					The minimum toll cost in a road trip from A to B
	  */
	 public static int canTravelMinCost(String A, String B, Graph<String, City> G) {
		 /* TODO ADD YOUR CODE HERE */
		 HeapPriorityQueue<Integer, String> heap = new HeapPriorityQueue<>();
		 Set<String> visited = new HashSet<>();
	 
		 heap.insert(0, A); // Add starting city with toll cost 0
		 while (!heap.isEmpty()) {
			 Entry<Integer, String> currCity = heap.removeMin();
			 if (currCity.getValue().equals(B)) {
				 return currCity.getKey(); // Found the ending city
			 } else if (!visited.contains(currCity.getValue())) {
				 visited.add(currCity.getValue());
				 List<City> neighbors = G.getNeighbors(currCity.getValue());
				 for (City neighbor : neighbors) {
					 if (!visited.contains(neighbor.getCityName())) {
						 int totalCost = currCity.getKey() + neighbor.getTollCost();
						 heap.insert(totalCost, neighbor.getCityName()); // Add the neighbor with updated cost
					 }
				 }
			 }
		 }
		 return -1; // Couldn't find a path from A to B
	 }
 }