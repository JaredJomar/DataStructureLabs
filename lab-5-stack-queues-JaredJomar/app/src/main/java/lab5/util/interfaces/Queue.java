package lab5.util.interfaces;

public interface Queue<E> {
	public void enqueue(E newEntry);
	public E dequeue();
	public E front();
	public boolean isEmpty();
	public int size(); 
	public void clear();
}