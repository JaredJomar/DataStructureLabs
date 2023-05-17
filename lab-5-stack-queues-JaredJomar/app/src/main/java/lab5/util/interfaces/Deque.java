package lab5.util.interfaces;
public interface Deque<E> {
	public void addFirst(E elm);
	public void addLast(E elm);
	public E removeFirst();
	public E removeLast();
	public E getFirst();
	public E getLast();
	public boolean removeFirstOccurrence(E elm);
	public boolean removeLastOccurrence(E elm);
	public int size();
	public boolean isEmpty();

	//DO NOT USE THIS IN EXERCISES
	public String[] toArray(); //DO NOT REMOVE, TEST WILL FAIL
}