package a5;

/**
 * A {@code TextJustifier} that aligns text to a column having a specified width
 * measured in characters such that the end of the text is aligned with
 * the right side of the column.
 */
public class FlushRightJustifier implements TextJustifier {

  /**
   * Aligns the specified string to a column having the specified width such that
   * the end of the text is aligned with the right side of the column.
   * The returned string is padded with spaces at the front of the string so that
   * the length of the string is equal to the column width.
   *
   * @param s     a string to align
   * @param width a column width in characters
   * @return a string aligned to a column of the specified width
   * @throws IllegalArgumentException if the length of s is greater than width
   */
  @Override
  public String justify(String s, int width) {
    String flushS = "";

    if (width > s.length()) {
      flushS = " ".repeat(width - s.length());
      flushS += s;
    }

    return flushS;
  }
}
