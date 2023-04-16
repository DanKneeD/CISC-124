package gameoflife;

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
      // DO SOMETHING HERE
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
}
