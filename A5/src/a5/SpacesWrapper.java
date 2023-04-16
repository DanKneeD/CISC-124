package a5;

import java.util.List;
import java.util.spi.ToolProvider;
import javax.lang.model.util.ElementScanner14;

/**
 * A class that wraps a string by breaking the string into lines
 * of length equal to or less than the desired wrapped line length.
 * The breaking occurs at spaces in the string where possible. If
 * a wrapped line contains no strings before the desired wrapped
 * line length, then the line is broken at the desired wrapped
 * line length.
 *
 */
public class SpacesWrapper extends AbstractStringWrapper {

  /**
   * Initializes this wrapper with the string to wrap and the
   * desired maximum wrapped line width.
   *
   * @param toWrap the string to wrap
   * @param targetWidth the desired maximum wrapped line width
   */
  public SpacesWrapper(String toWrap, int targetWidth) {
    super(toWrap, targetWidth);
  }

  /**
   * Wraps the string into separate lines of text.
   */

  public void wrap() {
    lines.clear();
    int wrapLength = toWrap.length();

    for (int x = 0; wrapLength > x; x++) {
      if (x + maxWidth < wrapLength) { // Looking in range
        boolean isFound = false;
        for (int y = x + maxWidth; y > x; y--) { //Looking backwords from max width
          if (toWrap.charAt(y) == ' ') {
            lines.add(toWrap.substring(x, y));
            x = y;
            isFound = true;
            break;
          }
        }
        if (!isFound) { //No space found
          lines.add(toWrap.substring(x, x + maxWidth));
          x += maxWidth - 1;
        }
      } else { // Out of Range
        lines.add(toWrap.substring(x, wrapLength));
        break;
      }
    }
  }

  public static void main(String[] args) {
    int width = 30;
    AbstractStringWrapper w = new SpacesWrapper(
      "ABC DEFGH I JKLMNOPQ RSTUVWXYZ",
      width
    );
    w.wrap();
    List<String> wrapped = w.getLines();
    String out = String.format(
      "width:%2d, lines:%2d, lines = %s",
      width,
      wrapped.size(),
      wrapped
    );
    System.out.println(out + "\n");

    width = 20;
    w.width(width);
    w.wrap();
    wrapped = w.getLines();
    out =
      String.format(
        "width:%2d, lines:%2d, lines = %s",
        width,
        wrapped.size(),
        wrapped
      );
    System.out.println(out + "\n");

    width = 5;
    w.width(width);
    w.wrap();
    wrapped = w.getLines();
    out =
      String.format(
        "width:%2d, lines:%2d, lines = %s",
        width,
        wrapped.size(),
        wrapped
      );
    System.out.println(out + "\n");

    width = 1;
    w.width(width);
    w.wrap();
    wrapped = w.getLines();
    out =
      String.format(
        "width:%2d, lines:%2d, lines = %s",
        width,
        wrapped.size(),
        wrapped
      );
    System.out.println(out + "\n");
  }
}
