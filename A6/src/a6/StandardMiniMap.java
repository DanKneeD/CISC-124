package a6;

/**
 * A map that prevents new key-value pairs from being added when the map is
 * full. This map does not maintain its keys in any particular order.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class StandardMiniMap<K, V> extends AbstractMiniMap<K, V> {

  /**
   * Initializes this map to be empty and having a capacity of 10 entries.
   */
  public StandardMiniMap() {
    super(10);
  }

  /**
   * Initializes this map as an empty map having the specified capacity.
   *
   * @param capacity the capacity of this map
   * @throws IllegalArgumentException if capacity is less than zero
   */
  public StandardMiniMap(int capacity) {
    super(capacity);
    if (capacity < 0) {
      throw new IllegalArgumentException();
    }
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
   * @param key   a key
   * @param value a value that the specified key maps to
   * @return true if the mapping is inserted into the map, false otherwise
   */
  @Override
  public boolean put(K key, V value) {
    if (this.containsKey(key)) {
      int index = this.indexOfKey(key);
      this.vals[index] = value;
    } else if (size + 1 <= this.capacity) {
      this.keys[size] = key;
      this.vals[size] = value;
      size += 1;
    } else {
      return false;
    }
    return true;
  }

  /**
   * Small test program.
   *
   * @param args not used
   */
  public static void main(String[] args) {
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
    MiniMap<String, Integer> tooSmallWordCount = new StandardMiniMap<>();

    // make a minimap that can hold all of the entries
    MiniMap<String, Integer> wordCount = new StandardMiniMap<>(16);

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

    System.out.println("StandardMiniMap");
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
