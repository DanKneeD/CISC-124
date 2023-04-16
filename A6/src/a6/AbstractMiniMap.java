package a6;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * A skeletal implementation of an array-based minimap having a fixed capacity.
 * This implementation uses two parallel arrays to store the keys and values of
 * this map.
 *
 * <p>
 * To implement a minimap, the programmer needs only to extend this class and
 * provide an implementation for the {@code put(K, V)} method.
 *
 * @param <K> the type of keys in this map
 * @param <V> the type of values in this map
 */
public abstract class AbstractMiniMap<K, V> implements MiniMap<K, V> {
  /**
   * The keys of this map
   */
  protected Object keys[];

  /**
   * The values of this map
   */
  protected Object vals[];

  /**
   * The number of entries of this map
   */
  protected int size;

  /**
   * The capacity of this map
   */
  protected final int capacity;

  /**
   * Initializes this map as an empty map having the specified capacity.
   *
   * @param capacity the capacity of this map
   * @throws IllegalArgumentException if capacity is less than zero
   */
  public AbstractMiniMap(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException();
    }
    this.keys = new Object[capacity];
    this.vals = new Object[capacity];
    this.capacity = capacity;
    this.size = 0;
  }

  /**
   * Returns the capacity (maximum number of elements) that this map can hold at
   * any one time.
   *
   * @return the capacity of this map
   */
  public final int capacity() {
    return this.capacity;
  }

  /**
   * Returns the number of key-value mappings held by this map.
   *
   * @return the number of key-value mappings held by this map
   */
  public final int size() {
    return this.size;
  }

  /**
   * NOT STRICTLY NECESSARY, BUT CONVENIENT TO HAVE. Students may choose to
   * implement this method and use it elsewhere where desired.
   *
   * <p>
   * Returns the index of the element equal to {@code key} if such an element
   * exists in the array {@code this.keys}, or {@code -1} otherwise.
   *
   * <p>
   * It is assumed that {@code key} is not {@code null}.
   *
   * @param key a key to search for in the array {@code this.keys}
   * @return the index of the key or -1 if the key is not in this.keys
   */
  protected int indexOfKey(Object key) {
    for (int x = 0; x < this.size; x++) {
      if (key.equals(this.keys[x])) {
        return x;
      }
    }
    return -1;
  }

  /**
   * NOT STRICTLY NECESSARY, BUT CONVENIENT TO HAVE. Students may choose to
   * implement this method and use it elsewhere where desired.
   *
   * <p>
   * Returns the index of the value equal to {@code val} if such an element exists
   * in the array {@code this.vals}, or {@code -1} otherwise.
   *
   * <p>
   * It is assumed that {@code key} is not {@code null}.
   *
   * @param val a value to search for in the array {@code this.vals}
   * @return the index of the value, or -1 if the value is not in this.vals
   */
  protected int indexOfValue(Object val) {
    for (int x = 0; x < this.size; x++) {
      if (val.equals(this.vals[x])) {
        return x;
      }
    }
    return -1;
  }

  /**
   * Returns true if the map contains a mapping for the specified key. More
   * formally, returns true if and only if this map has a key {@code k} such that
   * {@code k.equals(key)} is true.
   *
   * @param key a key to search for in this map
   * @return true if this map contains a mapping for the specified key
   */
  public boolean containsKey(Object key) {
    for (int x = 0; x < this.size; x++) {
      if (key.equals(this.keys[x])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns true if the map contains one or more keys that map to the specified
   * value. More formally, returns true if and only if this map has a value
   * {@code v} such that {@code v.equals(value)} is true.
   *
   * @param value a value to search for in this map
   * @return true if this map contains one or more mappings for the specified
   *         value
   */
  public boolean containsValue(Object value) {
    for (int x = 0; x < this.size; x++) {
      if (value.equals(this.vals[x])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the value that the specified key maps to, or {@code null} if
   * {@code containsKey(key)} is false.
   *
   * @param key the key to search for
   * @return the value that the specified key maps to, or {@code null} if the map
   *         does not contain the specified key
   */
  public V get(Object key) {
    if (this.containsKey(key)) {
      int index = this.indexOfKey(key);
      return (V) this.vals[index];
    }
    return null;
  }

  /**
   * Removes the mapping for a key from this map if it is present without
   * modifying the relative order of the remaining mappings.
   *
   * <p>
   * Returns the value to which this map previously associated the key, or null if
   * the map contained no mapping for the key.
   *
   * @param key the key to search for
   * @return the value to which this map previously associated the key, or null if
   *         the map contained no mapping for the key
   */
  @Override
  public V remove(Object key) { //TRIPLE CHECK THIS
    V removedVal = this.get(key);

    if (removedVal != null) {
      int index = this.indexOfKey(key);
      for (int x = index; x < size - 1; x++) {
        this.keys[x] = this.keys[x + 1];
        this.vals[x] = this.vals[x + 1];
      }

      this.keys[size - 1] = null;
      this.vals[size - 1] = null;

      size--;
    }

    return removedVal;
  }

  /**
   * Returns a string representation of this map. The returned string contains the
   * key-value pairs as strings enclosed in braces ({@code "{}"}). Adjacent
   * mappings are separated by the characters {@code ", "} (comma and space). Each
   * key-value mapping is rendered as the key followed by an equals sign
   * ({@code "="}) followed by the associated value. For example, a map containing
   * a string key {@code "a"} mapped to the integer value {@code 100} and a string
   * key {@code "b"} mapped to the integer value {@code 200} would have the string
   * representation:
   *
   * <p>
   * {@code "{a=100, b=200}"} or {@code "{b=100, a=200}"}
   *
   * <p>
   * depending on how the mappings are stored in the map.
   *
   *
   * @return a string representation of this map
   */
  public String toString() {
    StringJoiner sj = new StringJoiner(", ", "[", "]");
    for (int i = 0; i < this.keys.length; i++) {
      Object k = this.keys[i];
      if (k != null) {
        sj.add(k.toString() + "=" + this.vals[i].toString());
      }
    }
    return sj.toString();
  }
}
