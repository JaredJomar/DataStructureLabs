package lab7.interfaces;

@FunctionalInterface
public interface HashFunction<K> {
	/**
	 * Method that generates a unique hash code for a given key passed as parameter
	 * @param key - Key to compute the hash code
	 * @return Hash code associated to given key
	 */
	int hashCode(K key);
}