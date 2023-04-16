package gameoflife;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class Life2 {

  public static int numRows(boolean[][] cells) {
    return cells.length;
  }

  public static int numCols(boolean[][] cells) {
    if (numRows(cells) > 0) {
      return cells[0].length;
    }
    return 0;
  }

  public static boolean isValid(boolean[][] cells, int row, int col) {
    if (row < 0 || row >= numRows(cells)) {
      return false;
    } else if (col < 0 || col >= numCols(cells)) {
      return false;
    }
    return true;
  }

  public static boolean[][] clone(boolean[][] cells) {
    int rows = numRows(cells);
    int cols = numCols(cells);

    boolean[][] copy = new boolean[rows][cols];

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        copy[r][c] = cells[r][c];
      }
    }

    return copy;
  }

  public static void printCells(boolean[][] cells) {
    int rows = numRows(cells);
    int cols = numCols(cells);

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (cells[r][c] == true) {
          System.out.print("#");
        } else {
          System.out.print("-");
        }
      }
      System.out.println("");
    }
  }

  public static boolean[][] neighborhood(boolean[][] cells, int row, int col) {
    if (!isValid(cells, row, col)) {
      throw new IllegalArgumentException();
    }

    boolean[][] nhood = new boolean[3][3];

    int left = col - 1;
    int top = row - 1;

    for (int r = 0; r < 3; r++) {
      int cellRow = top + r;
      for (int c = 0; c < 3; c++) {
        int cellsCol = left + c;

        if (isValid(cells, cellRow, cellsCol)) {
          nhood[r][c] = cells[cellRow][cellsCol];
        }
      }
    }

    return (nhood);
  }

  public static boolean isAlive(boolean[][] cells, int row, int col) {
    if (!isValid(cells, row, col)) {
      throw new IllegalArgumentException();
    }

    return cells[row][col];
  }

  public static int numAlive(boolean[][] cells) {
    int numCounter = 0;
    int rows = numRows(cells);
    int cols = numCols(cells);

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (cells[r][c] == true) {
          numCounter += 1;
        }
      }
    }
    return numCounter;
  }

  public static boolean isBorn(boolean[][] cells, int row, int col) {
    /**
     * Returns true if the cell {@code cells[row][col]} becomes alive in the next
     * generation, false otherwise. The method does not modify the array; it
     * simply determines whether or not a cell should become alive in the
     * next generation.
     *
     * <p>
     * A cell becomes alive if it is currently dead and its neighborhood has
     * exactly 3 alive cells.
     *
     * @param cells a two-dimensional array
     * @param row   a row index
     * @param col   a column index
     * @return true if the cell {@code cells[row][col]} becomes alive in the next
     *         generation, false otherwise
     * @throws IllegalArgumentException if row or col is not a valid index for
     *                                   cells
     */

    if (!isValid(cells, row, col)) {
      throw new IllegalArgumentException();
    } else if (isAlive(cells, row, col)) {
      return false;
    }

    boolean[][] neigh = neighborhood(cells, row, col);
    int neighsAlive = numAlive(neigh);

    if (neighsAlive == 3) {
      return true;
    }

    return false;
  }

  public static boolean survives(boolean[][] cells, int row, int col) {
    /**
     * Returns true if the cell {@code cells[row][col]} survives into the next
     * generation, false otherwise. The method does not modify the array; it
     * simply determines whether or not a cell should remain alive in the
     * next generation.
     *
     * <p>
     * A cell survives into the next generation if it is currently alive and if its
     * 8 neighbors have 2 or 3 alive cells.
     *
     * @param cells a two-dimensional array
     * @param row   a row index
     * @param col   a column index
     * @return true if the cell {@code cells[row][col]} survives into the next
     *         generation, false otherwise
     * @throws IllegalArgumentException if row or col is not a valid index for
     *                                   cells
     */

    if (!isValid(cells, row, col)) {
      throw new IllegalArgumentException();
    } else if (!isAlive(cells, row, col)) {
      return false;
    }

    boolean[][] neigh = neighborhood(cells, row, col);
    int neighsAlive = numAlive(neigh) - 1;

    if (neighsAlive == 2 || neighsAlive == 3) {
      return true;
    }

    return false;
  }

  public static void evolve(boolean[][] cells) {
    /**
     * Updates {@code cells} so that it is equal to the next generation of cells.
     *
     * <p>
     * See the assignment document for details.
     *
     * @param cells a two-dimensional array
     */

    int rows = numRows(cells);
    int cols = numCols(cells);

    //correct rows and cols

    boolean[][] copy = clone(cells);

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        if (isBorn(copy, r, c)) {
          cells[r][c] = true;
        } else if (survives(copy, r, c)) {
          cells[r][c] = true;
        } else {
          cells[r][c] = false;
        }
      }
    }
  }

  public static void randomize(boolean[][] cells) {
    /**
     * Randomly sets each element of {@code cells} to true or false with
     * equal probability.
     *
     * @param cells a two-dimensional array
     */

    int rows = numRows(cells);
    int cols = numCols(cells);

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        Random rd = new Random();
        cells[r][c] = rd.nextBoolean();
      }
    }
  }

  public static boolean insert(
    boolean[][] pattern,
    int row,
    int col,
    boolean[][] cells
  ) {
    /**
     * Inserts a pattern of cells into another array of cells. The pattern replaces
     * the elements in {@code cells} starting at {@code cells[row][col]}. The
     * pattern must fit completely within the array {@code cells}, otherwise no
     * cells are replaced and false is returned.
     *
     * @param pattern a 2d array of replacement cells
     * @param row     the row index of the upper-left corner of cells where the
     *                replacement should begin
     * @param col     the column index of the upper-left corner of cells where the
     *                replacement should begin
     * @param cells   a 2d array of cells
     * @return true if the pattern fits within cells, false otherwise
     * @throws IllegalArgumentException if row or col is not a valid index for cells
     */

    int patRows = numRows(pattern);
    int patCols = numCols(pattern);

    //checking if starting or final cells are valid
    if (
      !isValid(cells, row, col) ||
      !isValid(cells, row + patRows - 1, col + patCols - 1)
    ) {
      throw new IllegalArgumentException();
    }

    for (int r = row; r < patRows + row; r++) {
      for (int c = col; c < patCols + col; c++) {
        cells[r][c] = pattern[r - row][c - col];
      }
    }
    return true;
  }

  public static boolean[][] read(String filename) {
    /**
     * Reads a pattern of cells from a file. The pattern format is identical
     * to the output of {@code printCells}. The pattern files must be located
     * in the {@code patterns} folder of the eclipse project.
     *
     * @param filename the filename of a pattern file
     * @return a 2d array of cells
     */
    try {
      Path path = FileSystems.getDefault().getPath("patterns", filename);
      List<String> lines = Files.readAllLines(path);

      // your code starts here
      int rows = lines.size();
      int cols = lines.get(0).length();

      boolean[][] tempCells = new boolean[rows][cols];

      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < cols; c++) {
          if (lines.get(r).substring(c, c + 1).equals("#")) {
            tempCells[r][c] = true;
          }
        }
      }

      return tempCells;
    } catch (Exception ex) {
      // some sort of error occurred while reading the file
      ex.printStackTrace();
      System.exit(1);
    }
    return null;
  }
}
