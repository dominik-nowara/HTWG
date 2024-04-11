// O. Bittel
// 26.09.2022

import java.util.Iterator;

/**
 * Collection of entries.
 * @author oliverbittel
 * @since 22.02.2017
 * @param <K> the type of keys maintained by this map.
 * @param <V> the type of mapped values.
 */
public interface Dictionary<K,V> extends Iterable<Dictionary.Entry<K,V>> {
	/**
	 * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced by the specified value.
     * Returns the previous value associated with key,
     * or null if there was no mapping for key.
	 * @param key key with which the specified value is to be associated
	 * @param value value to be associated with the specified key
	 * @return the previous value associated with key, or null if there was no mapping for key.
	 */
	V insert(K key, V value);

	/**
	 * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
	 * @param key the key whose associated value is to be returned.
	 * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	 */
	V search(K key);

	/**
	 * Removes the key-vaue-pair associated with the key.
     * Returns the value to which the key was previously associated,
     * or null if the key is not contained in the dictionary.
	 * @param key key whose mapping is to be removed from the map.
	 * @return the previous value associated with key, or null if there was no mapping for key. 
	 */
	V remove(K key);

	/**
	 * Returns the number of elements in this dictionary.
	 * @return the number of elements in this dictionary.
	 */
	int size();
	
	/**
	 * Returns an iterator over the entries in this dictionary. 
	 * There are no guarantees concerning the order in which the elements are returned 
	 * (unless this dictionary is an instance of some class that provides a guarantee).
	 * @return an Iterator over the entries in this dictionary
	 */
	@Override
	public Iterator<Entry<K, V>> iterator();
	
	/**
	 * A dictionary entry (key-value pair).
	 * @param <K> Key (must be immutable).
	 * @param <V> Value.
	 */
	class Entry<K,V> {
		private final K key;
		private V value;

		/**
		 *
		 * @param key
		 * @param value
		 */
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Returns the key corresponding to this entry.
		 * @return the key corresponding to this entry
		 */
		public K getKey() {return key;}

		/**
		 * Returns the value corresponding to this entry.
		 * @return the value corresponding to this entry
		 */
		public V getValue() {return value;}

		/**
		 * Replaces the value corresponding to this entry with the specified value.
		 * @param v new value to be stored in this entry
		 * @return old value corresponding to the entry
		 */
		public V setValue(V v) {
			V retVal = value;
			value = v;
			return retVal;
		}
	}
}
