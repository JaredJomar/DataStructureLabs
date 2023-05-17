package lab7.util.set;

import java.util.Comparator;
import java.util.Iterator;

import lab7.interfaces.List;
import lab7.interfaces.Set;
import lab7.util.hashTable.HashTableOA;

public class HashSet<E> implements Set<E> {
	
	/**
	 * TODO EXERCISE 3:
	 * 
	 * Java includes a Set implementation called 
	 * HashSet that is backed by a hash table. 
	 * 
	 * In this exercise, you create your own version of HashSet. 
	 * 
	 * Create an implementation called HashSet that uses 
	 * an embedded hash table (using object composition).
	 * 
	 * NOTE: MAKE SURE TO IMPLEMENT THE rehash METHOD FROM EXERCISE 1 ON HashTableOA 
	 */
	
	/* DO NOT MODIFY, TESTS WILL FAIL */
	private HashTableOA<E, Object> ht; 
	private static final int DEFAULT_SIZE = 11;
	
	public HashSet(int initialCapacity) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		this.ht = new HashTableOA<E, Object>(initialCapacity,(key)->{
			int r = 0;
			for (char c: key.toString().toCharArray()) {
				r += c;
			}
			return r;
		});
	}
	
	public HashSet() {
		this(DEFAULT_SIZE);
	}

	@Override
	public Iterator<E> iterator() {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		return ht.getKeys().iterator();
	}

	@Override
	public boolean add(E obj) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		if (ht.containsKey(obj)) {
			return false;
		} else {
			ht.put(obj, new Object());
			return true;
		}
	}

	@Override
	public boolean isMember(E obj) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		return ht.containsKey(obj);
	}

	@Override
	public boolean remove(E obj) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		if (!ht.containsKey(obj)) {
			return false;
		}
		ht.remove(obj);
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return ht.isEmpty();
	}

	@Override
	public int size() {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		return ht.size();
	}

	@Override
	public void clear() {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		ht.clear();
	}

	@Override
	public Set<E> union(Set<E> S2) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		Set<E> unionSet = new HashSet<>();
		for (E element : this) {
			unionSet.add(element);
		}
		for (E element : S2) {
			if (!unionSet.isMember(element)) {
				unionSet.add(element);
			}
		}
		return unionSet;
	}

	@Override
	public Set<E> difference(Set<E> S2) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		Set<E> diffSet = new HashSet<>();
		for (E element : this) {
			if (!S2.isMember(element)) {
				diffSet.add(element);
			}
		}
		return diffSet;
	}

	@Override
	public Set<E> intersection(Set<E> S2) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		Set<E> interSet = new HashSet<>();
		for (E element : this) {
			if (S2.isMember(element)) {
				interSet.add(element);
			}
		}
		return interSet;
	}

	@Override
	public boolean isSubSet(Set<E> S2) {
		/* TODO EXERCISE 2: ADD YOUR CODE HERE */
		for (E element : this) {
			if (!S2.isMember(element)) {
				return false;
			}
		}
		return true;
	}

	/* DO NOT REMOVE, TESTS WILL FAIL */
	public HashTableOA<E, Object> getHT() {
		return this.ht;
	}
	
	public Object[] toArray() {
		List<E> elements = ht.getKeys();
		Object[] result = new Object[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			result[i] = elements.get(i);
		}
		
		return result;
	}
}
