package lab9.interfaces;

import lab9.tree.BTNode;

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
	
	// DO NOT DELETE, TESTS WILL FAIL
	public BTNode<K, V> getRoot();
}