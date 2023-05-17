package lab9.interfaces;

public interface List<E> extends Iterable<E>{
	public int size();
	public boolean isEmpty();
	public boolean contains(E e);
	public int firstIndexOf(E e);
	public int lastIndexOf(E e);
	public void add(E e);
	public void add(E e, int index);
	public E get(int index);
	public E remove(int index);
    public boolean remove(E e);
    public int removeAll(E e);
	public E set(int index, E newElement);
	public void clear();
}
