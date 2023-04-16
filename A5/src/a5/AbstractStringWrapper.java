package a5;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class for classes that try to wrap strings to a desired maximum
 * width. Wrapping a string breaks the string into separate lines so that the
 * length of each line is (ideally) less than or equal to some user-defined
 * maximum width.
 * 
 * <p>
 * This class provides a skeletal implementation of a string wrapping class. It
 * provides storage for the string that is being wrapped, the desired maximum
 * width of each line, and the list of lines comprising the wrapped string.
 * 
 * <p>
 * Subclasses should override the {@code wrap()} method which implements a
 * string wrapping strategy.
 *
 */
public abstract class AbstractStringWrapper {

	/**
	 * The string that this StringWrapper wraps.
	 */
	protected String toWrap;

	/**
	 * The desired maximum width of each line of the wrapped string.
	 */
	protected int maxWidth;

	/**
	 * The list containing the lines of the wrapped string.
	 */
	protected List<String> lines;

	/**
	 * Initializes this {@code StringWrapper} with a string to wrap and the
	 * desired maximum wrapped line width.
	 * 
	 * @param toWrap      the string to wrap
	 * @param targetWidth the desired maximum width of each line of the wrapped
	 *                    string
	 * @throws IllegalArgumentException if targetWidth is less than 1
	 */
	public AbstractStringWrapper(String toWrap, int targetWidth) {
		if (targetWidth < 1) {
			throw new IllegalArgumentException();
		}
		this.toWrap = toWrap;
		this.maxWidth = targetWidth;
		this.lines = new ArrayList<>();
	}

	/**
	 * Returns the desired maximum width of each line of the wrapped string.
	 * 
	 * @return the desired maximum width of each line of the wrapped string
	 */
	public int width() {
		return this.maxWidth;
	}

	/**
	 * Sets the desired maximum width of each line of the wrapped string. The string
	 * is not automatically re-wrapped to the new width.
	 * 
	 * <p>
	 * The caller should call {@code wrap(String)} or {@code wrap()} after calling
	 * this method to cause the string to be re-wrapped.
	 * 
	 * @param maxWidth the desired maximum width of each line of the wrapped string
	 * @throws IllegalArgumentException if targetWidth is less than 1
	 */
	public void width(int maxWidth) {
		if (maxWidth < 1) {
			throw new IllegalArgumentException();
		}
		this.maxWidth = maxWidth;
	}

	/**
	 * Returns a list containing the lines of the wrapped string.
	 * 
	 * <p>
	 * The caller should call {@code wrap(String)} or {@code wrap()} before calling
	 * this method to cause the string to be wrapped, otherwise an empty list may be
	 * returned.
	 * 
	 * @return a list containing the lines of the wrapped string, or an empty list
	 *         if the string has not been wrapped yet
	 */
	public List<String> getLines() {
		return new ArrayList<>(this.lines);
	}

	/**
	 * Changes the string to be wrapped to the specified string, and wraps the
	 * string.
	 * 
	 * @param s the string to be wrapped
	 */
	public void wrap(String s) {
		this.toWrap = s;
		this.wrap();
	}

	/**
	 * Wraps the string breaking it into separate lines. Subclasses will implement
	 * different strategies for wrapping strings and should try to ensure that the
	 * length of each line is less than or equal to the desired maximum width;
	 * however, not all wrapping strategies will be able to guarantee that the
	 * length of all lines of the wrapped string are less than or equal to the
	 * desired maximum width.
	 * 
	 * <p>
	 * When overriding this method, subclasses should first clear this object's list
	 * of strings that hold the wrapped lines of text. Then, subclasses should
	 * wrap the the string adding the wrapped lines to this object's list.
	 */
	public abstract void wrap();

	/**
	 * Returns the string that is being wrapped. To get the wrapped version of the
	 * string, callers should use {@code getLines()).
	 * 
	 * @return the string that is being wrapped
	 */
	public String toString() {
		return this.toWrap;
	}
}
