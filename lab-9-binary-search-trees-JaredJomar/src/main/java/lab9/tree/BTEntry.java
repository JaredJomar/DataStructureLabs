package lab9.tree;

import lab9.interfaces.Entry;

/**
 * Class that will represent an Entry for the BST.
 * This class will hold the key-value pairs that will be store in the BST
 * @author Fernando J. Bermudez - bermed28 
 *
 * @param <K> - represents a key
 * @param <V> - represents the value pointed by the key
 */
public class BTEntry<K, V> implements Entry<K, V> {

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