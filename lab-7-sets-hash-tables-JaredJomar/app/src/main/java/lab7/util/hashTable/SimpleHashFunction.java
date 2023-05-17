package lab7.util.hashTable;

import lab7.interfaces.HashFunction;

public class SimpleHashFunction<K> implements HashFunction<K> {
	/**
	 * This is only here for reference, 
	 * in the actual implementation this is 
	 * created and used as a lambda function
	 */
	@Override
	public int hashCode(K key) {
		String temp = key.toString();
		int result = 0;
		for (int i = 0; i < temp.length(); i++)
			result += temp.charAt(i);
		return result;
	}

}