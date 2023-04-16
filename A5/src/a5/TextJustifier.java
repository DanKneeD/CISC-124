package a5;

import java.util.List;

/**
 * A {@code TextJustifier} object aligns text to a column having a specified
 * width measured in characters. Common types of alignment include:
 * 
 * <ul>
 * <li>flush left, or left justified, where the beginning of the text is aligned
 * with the left side of the column,
 * <li>flush right, or right justified, where the end of the text is aligned with
 * the right side of the column, and
 * <li>center where the text is aligned to the middle of the column
 *
 */
public interface TextJustifier {

	/**
	 * Aligns the specified string to a column having the specified width. The
	 * returned string contains the text of the specified string with whitespace
	 * inserted so that the returned string has length equal to the specified width.
	 * 
	 * @param s     a string to align
	 * @param width a column width in characters
	 * @return a string aligned to a column of the specified width
	 * @throws IllegalArgumentException if the length of s is greater than width
	 */
	public String justify(String s, int width);

	/**
	 * Aligns each string in the specified list to a column having the specified
	 * width. This method behaves as though it calls {@code justify(String, int)}
	 * for each string in the specified list.
	 * 
	 * @param lines a list of strings
	 * @param width a column width in characters
	 * @return a list of strings aligned to a column of the specified width
	 * @throws IllegalArgumentException if the length of any string in lines is
	 *                                  greater than width
	 */
	default public List<String> justify(List<String> lines, int width) {
		for (String s : lines) {
			this.justify(s, width);
		}
		return lines;
	}
	
}
