package a6;

import java.util.Comparator;

/**
 * A map that allows new key-value pairs to be added when the map is full. When
 * adding a new key and its value to a full map, the oldest mapping is first
 * removed from the map and then the new mapping is added.
 *
 * <p>
 * This map maintains its mappings in insertion order (the mappings are held
 * from oldest to newest).
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class SortedMiniMap<K, V> extends AbstractMiniMap<K, V> {
  private Comparator<K> comp;

  /**
   * Initializes this map to be empty and having a capacity of 10 entries.
   * Keys are compared using the comparator {@code comp}.
   *
   * @param comp the comparator to be used when comparing keys
   * @throws NullPointerException if comp is null
   */
  public SortedMiniMap(Comparator<K> comp) {
    super(10);
    if (comp == null) {
      throw new NullPointerException();
    }
    this.comp = comp;
  }

  /**
   * Initializes this map as an empty map having the specified capacity.
   * Keys are compared using the comparator {@code comp}.
   *
   * @param capacity the capacity of this map
   * @param comp the comparator to be used when comparing keys
   * @throws IllegalArgumentException if capacity is less than zero
   * @throws NullPointerException if comp is null
   */
  public SortedMiniMap(int capacity, Comparator<K> comp) {
    super(capacity);
    if (capacity < 0) {
      throw new IllegalArgumentException();
    } else if (comp == null) {
      throw new NullPointerException();
    }

    this.comp = comp;
  }

  /**
   * Associates the specified value with the specified key in this map. If the map
   * previously contained a mapping for the key, the old value is replaced by the
   * specified value and {@code true} is returned.
   *
   * <p>
   * If the map is full and the specified key is not in the map, then {@code false}
   * is returned.
   *
   * <p>
   * If the map is not full, and the map does not contain a mapping for the key,
   * then the key-value pair is inserted into this map so that the set of keys are
   * in sorted order.
   *
   * @param key   a key
   * @param value a value that the specified key maps to
   * @return true
   */
  @Override
  @SuppressWarnings("unchecked")
  public boolean put(K key, V value) {
    if (containsKey(key)) {
      int index = indexOfKey(key);
      vals[index] = value;

      return true;
    } else if (size >= capacity) {
      return false;
    } else {
      int insertIndex = size;
      // Finding the correct position for the new key
      for (int x = size - 1; x >= 0; x--) {
        if (comp.compare((K) keys[x], key) > 0) {
          // Moving elements to the right
          keys[x + 1] = keys[x];
          vals[x + 1] = vals[x];
          insertIndex = x;
        } else {
          // New key location found
          break;
        }
      }

      keys[insertIndex] = key;
      vals[insertIndex] = value;
      size++;

      return true;
    }
  }

  /**
   * Small test program.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    // get a Comparator that can compare strings
    Comparator<String> comp = Comparator.naturalOrder();

    String lyric =
      "baby shark doo doo doo doo " +
      "baby shark doo doo doo doo " +
      "baby shark doo doo doo doo baby shark " +
      "mommy shark doo doo doo doo " +
      "mommy shark doo doo doo doo " +
      "mommy shark doo doo doo doo mommy shark " +
      "daddy shark doo doo doo doo " +
      "daddy shark doo doo doo doo " +
      "daddy shark doo doo doo doo daddy shark " +
      "grandma shark doo doo doo doo " +
      "grandma shark doo doo doo doo " +
      "grandma shark doo doo doo doo grandma shark " +
      "grandpa shark doo doo doo doo " +
      "grandpa shark doo doo doo doo " +
      "grandpa shark doo doo doo doo grandpa shark " +
      "let's go hunt doo doo doo doo " +
      "let's go hunt doo doo doo doo " +
      "let's go hunt doo doo doo doo let's go hunt " +
      "run away doo doo doo doo " +
      "run away doo doo doo doo " +
      "run away doo doo doo doo run away ah!";

    // make a minimap that can hold some of the entries
    MiniMap<String, Integer> tooSmallWordCount = new SortedMiniMap<>(comp);

    // make a minimap that can hold all of the entries
    MiniMap<String, Integer> wordCount = new SortedMiniMap<>(16, comp);

    for (String word : lyric.split(" ")) {
      if (tooSmallWordCount.containsKey(word)) {
        int count = tooSmallWordCount.get(word);
        tooSmallWordCount.put(word, count + 1);
      } else {
        tooSmallWordCount.put(word, 1);
      }
      if (wordCount.containsKey(word)) {
        int count = wordCount.get(word);
        wordCount.put(word, count + 1);
      } else {
        wordCount.put(word, 1);
      }
    }

    System.out.println("SortedMiniMap");
    System.out.println("Using a map whose capacity is too small...");
    System.out.println("capacity : " + tooSmallWordCount.capacity());
    System.out.println("size     : " + tooSmallWordCount.size());
    System.out.println("entries  : " + tooSmallWordCount);
    System.out.println();

    System.out.println("Using a map with sufficient capacity...");
    System.out.println("capacity : " + wordCount.capacity());
    System.out.println("size     : " + wordCount.size());
    System.out.println("entries  : " + wordCount);
  }
}
