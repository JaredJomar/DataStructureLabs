package lab6.RecLinkedList;

import java.util.Comparator;

public interface List<E> {
	public void add(E e);
	public void add(int index, E e) throws IndexOutOfBoundsException;
	public boolean remove(int index);
	public int removeAll(E e);
	public E get(int index) throws IndexOutOfBoundsException;
	public E set(int index, E e) throws IndexOutOfBoundsException;
	public E first();
	public E last();
	public int firstIndex(E e);
	public int lastIndex(E e);
	public int size();
	public boolean isEmpty();
	public boolean contains(E e);
	public void clear();
	public E[] toArray();
	/*TODO EXERCISE 3: ADD YOUR CODE HERE*/
	public void sort(Comparator<E> comparator);
}
