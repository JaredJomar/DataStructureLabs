package lab5.util.interfaces;

public interface Stack<E> {
	public void push(E newEntry);
	public E pop();
	public E top();
	public boolean isEmpty();
	public int size(); 
	public void clear();
}