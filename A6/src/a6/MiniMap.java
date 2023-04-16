package a6;

/**
 * A small, fixed capacity map. A map is an object that maps keys to values. A
 * map cannot contain duplicate keys; each key can map to at most one value.
 * 
 * <p>
 * This interface assumes that keys and values are never equal to {@code null}.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MiniMap<K, V> {
	/**
	 * Returns the capacity (maximum number of elements) that this map can hold at
	 * any one time.
	 * 
	 * @return the capacity of this map
	 */
	public int capacity();

	/**
	 * Returns true if the map contains a mapping for the specified key. More
	 * formally, returns true if and only if this map has a key {@code k} such that
	 * {@code k.equals(key)} is true.
	 * 
	 * @param key a key to search for in this map
	 * @return true if this map contains a mapping for the specified key
	 */
	public boolean containsKey(Object key);

	/**
	 * Returns true if the map contains one or more keys that map to the specified
	 * value. More formally, returns true if and only if this map has a value
	 * {@code v} such that {@code v.equals(value)} is true.
	 * 
	 * @param value a value to search for in this map
	 * @return true if this map contains one or more mappings for the specified
	 *         value
	 */
	public boolean containsValue(Object value);

	/**
	 * Returns the value that the specified key maps to, or {@code null} if
	 * {@code containsKey(key)} is false.
	 * 
	 * @param key the key to search for
	 * @return the value that the specified key maps to, or {@code null} if the map
	 *         does not contain the specified key
	 */
	public V get(Object key);

	/**
	 * Returns true if this map has no mappings, false otherwise.
	 * 
	 * @return true if this map has no mappings, false otherwise
	 */
	public default boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Associates the specified value with the specified key in this map. If the map
	 * previously contained a mapping for the key, the old value is replaced by the
	 * specified value.
	 * 
	 * <p>
	 * A fixed capacity map may or may not allow a mapping to be inserted if this
	 * map is full. If a key-value pair is inserted into a full map, then an
	 * existing mapping must be overwritten, and it is up to the implementing class
	 * to determine which mapping is overwritten.
	 * 
	 * <p>
	 * Returns true if the mapping is inserted into the map, false otherwise.
	 * 
	 * @param key   a key
	 * @param value a value that the specified key maps to
	 * @return true if the mapping is inserted into the map, false otherwise
	 */
	public boolean put(K key, V value);

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * <p>
	 * Returns the value to which this map previously associated the key, or null if
	 * the map contained no mapping for the key.
	 * 
	 * @param key the key to search for
	 * @return the value to which this map previously associated the key, or null if
	 *         the map contained no mapping for the key
	 */
	public V remove(Object key);

	/**
	 * Returns the number of key-value mappings held by this map.
	 * 
	 * @return the number of key-value mappings held by this map
	 */
	public int size();

	
	/**
	 * Returns a string representation of this map.
	 * 
	 * @return a string representation of this map
	 */
	public String toString();
}
