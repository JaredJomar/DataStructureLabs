package lab7.util.hashTable;

import java.io.PrintStream;

import lab7.interfaces.HashFunction;
import lab7.interfaces.List;
import lab7.interfaces.Map;
import lab7.util.LinkedList;

/**
 * Implementation of the Map ADT using a Open-Addressing Hash Table Structure
 * 
 * @author Fernando J. Bermudez - bermed28
 *
 * @param <K>
 * @param <V>
 */
public class HashTableOA<K, V> implements Map<K, V> {

	@SuppressWarnings("hiding")
	private class Bucket<K, V> {
		private K key;
		private V value;
		private boolean inUse;

		public Bucket(K key, V value, boolean inUse) {
			this.key = key;
			this.value = value;
			this.inUse = inUse;
		}

		public Bucket() {
			this(null, null, false);
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public boolean isInUse() {
			return inUse;
		}

		public void setInUse(boolean isInUse) {
			this.inUse = isInUse;
		}

		public void release() {
			this.key = null;
			this.value = null;
			this.inUse = false;

		}
	}

	private Bucket<K, V>[] buckets;
	private int currentSize;
	private HashFunction<K> hashFunction;
	private static final int DEFAULT_SIZE = 11; // Java uses a default size of 11
	/* TODO EXERCISE 1: ADD YOUR loadFactor CONSTANT HERE */
	private static final double loadFactor = 0.75;
	private static int rehashCount = 0; /* DO NOT REMOVE, TESTS WILL FAIL */

	@SuppressWarnings("unchecked")
	public HashTableOA(int initialCapacity, HashFunction<K> hashFunction) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException("Capacity must be at least 1");

		this.currentSize = 0;
		this.hashFunction = hashFunction;
		this.buckets = new Bucket[initialCapacity];
		for (int i = 0; i < buckets.length; i++)
			buckets[i] = new Bucket<>();
	}

	public HashTableOA() {
		/**
		 * Simple Hash Function Implementation Lambda passed to constructor (See
		 * SimpleHashFunction.java for class definition reference)
		 */
		this(DEFAULT_SIZE, (key) -> {
			String temp = key.toString();
			int result = 0;
			for (int i = 0; i < temp.length(); i++)
				result += temp.charAt(i);
			return result;
		});
	}

	@Override
	public V get(K key) {
		if (key == null)
			throw new IllegalArgumentException("Key cannot be null");

		/**
		 * First, find the bucket this key is supposed to be in if no collisions
		 * happened by using the hash function
		 */
		int targetBucket = hashFunction.hashCode(key) % buckets.length;

		/**
		 * If the target bucket has an entry and it is the one we are looking for return
		 * the value associated to that entrie's key
		 */
		if (buckets[targetBucket].isInUse() && buckets[targetBucket].getKey().equals(key))
			return buckets[targetBucket].getValue();
		else
			/**
			 * If it is empty or it has a different entry than the one we are looking for,
			 * we need to check the other buckets using linear probing.
			 */
			return getLinearProbing(key, targetBucket);
	}

	private V getLinearProbing(K key, int start) {
		int index = getBucket(key, start); // Try to find correct bucket
		if (index != -1) // Found it!
			return buckets[index].getValue();
		return null;
	}

	/**
	 * Helper method to find the bucket containing a specific key by probing.
	 * 
	 * @param key   The key to search for
	 * @param start The index where the collision happened
	 * @return The index where the key was found (or -1 if not found)
	 */
	private int getBucket(K key, int start) {
		int n = buckets.length;
		/**
		 * Keep checking buckets until we find the key, or we wrap-around to the start
		 * position.
		 */
		for (int i = (start + 1) % n; i != start; i = (i + 1) % n) {
			Bucket<K, V> bucket = buckets[i];
			/**
			 * If we reach a bucket not in use, then our key will not be found, because no
			 * value has been added here through probing.
			 */
			if (!bucket.isInUse())
				return -1;
			else if (bucket.getKey().equals(key))
				return i; // Found it!
		}
		return -1; // Did not find it even after checking all of the remaining buckets
	}

	@Override
	public void put(K key, V value) {
		if (key == null || value == null)
			throw new IllegalArgumentException("Parameters cannot be null");

		/**
		 * Can't have 2 entries with the same key, so remove any entries with the same
		 * key passed as parameter.
		 */
		remove(key);

		/*
		 * TODO EXERCISE 1: UNCOMMENT AND ADD YOUR LOGIC TO TRIGGER THE rehash() METHOD
		 * if(conditionToTriggerRehash) { rehashCount++; // DO NOT REMOVE, TESTS WILL
		 * FAIL }
		 */
		double currentLoadFactor = (double) currentSize / buckets.length;
		if (currentLoadFactor >= loadFactor) {
			rehash();
			rehashCount++;
		}
		// System.out.println("Adding Element with key: " + key); // Uncomment for
		// debugging

		/**
		 * Find target bucket associated to given key, and insert it
		 */
		int targetBucket = hashFunction.hashCode(key) % buckets.length;
		if (!buckets[targetBucket].isInUse())
			buckets[targetBucket] = new Bucket<>(key, value, true);
		else
			putLinearProbing(key, value, targetBucket);
		currentSize++;
	}

	private void putLinearProbing(K key, V value, int start) {
		int n = buckets.length;
		for (int i = (start + 1) % n; i != start; i = (i + 1) % n) {
			Bucket<K, V> bucket = buckets[i];
			if (!bucket.isInUse()) {
				buckets[i] = new Bucket<>(key, value, true);
				return;
			}
		}
	}

	/* TODO EXERCISE 1: ADD YOUR rehash() METHOD IMPLEMETNATION HERE */
	private void rehash() {
		int newCapacity = buckets.length * 2;
		Bucket<K, V>[] newBuckets = new Bucket[newCapacity];
		for (int i = 0; i < newCapacity; i++) {
			newBuckets[i] = new Bucket<>(null, null, false);
		}
		for (Bucket<K, V> bucket : buckets) {
			if (bucket.isInUse()) {
				int targetBucket = Math.abs(hashFunction.hashCode(bucket.getKey())) % newCapacity;
				newBuckets[targetBucket] = bucket;
			}
		}
		buckets = newBuckets;
	}

	@Override
	public V remove(K key) {
		if (key == null)
			throw new IllegalArgumentException("Parameter cannot be null.");

		/* First we determine the bucket corresponding to this key */
		int targetBucket = hashFunction.hashCode(key) % buckets.length;
		Bucket<K, V> b = buckets[targetBucket];
		if (b.isInUse() && b.getKey().equals(key)) {
			V v = b.getValue();
			b.release();
			currentSize--;
			return v;
		} else
			return removeLinearProbing(key, targetBucket);
	}

	private V removeLinearProbing(K key, int start) {
		int index = getBucket(key, start);
		if (index != -1) { // Found it!
			V v = buckets[index].getValue();
			buckets[index].release();
			currentSize--;
			return v;
		} else
			return null;
	}

	@Override
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	public List<K> getKeys() {
		List<K> result = new LinkedList<>();
		for (Bucket<K, V> bucket : buckets) {
			if (bucket.isInUse())
				result.add(0, bucket.getKey());
		}
		return result;
	}

	@Override
	public List<V> getValues() {
		List<V> result = new LinkedList<>();
		for (Bucket<K, V> bucket : buckets) {
			if (bucket.isInUse())
				result.add(0, bucket.getValue());
		}
		return result;
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
		for (int i = 0; i < buckets.length; i++)
			buckets[i].release();

		currentSize = 0;
	}

	@Override
	public void print(PrintStream out) {
		/* For each bucket in use in the hash table, print the elements */
		for (int i = 0; i < buckets.length; i++) {
			Bucket<K, V> b = buckets[i];
			if (b.isInUse())
				out.printf("(%s, %s)\n", b.getKey(), b.getValue());
		}
	}

	/* DO NOT REMOVE, TESTS WILL FAIL */
	public int getRehashCount() {
		return rehashCount;
	}

	public void setRehashCount(int count) {
		rehashCount = count;
	}

	public Object[] toArray() {
		List<V> elements = getValues();
		Object[] result = new Object[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			result[i] = elements.get(i);
		}

		return result;
	}
}