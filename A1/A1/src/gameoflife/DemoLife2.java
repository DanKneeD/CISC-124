package gameoflife;

import java.util.Arrays;

public class DemoLife2 {

  public static void main(String[] args) {
    boolean[][] cells = new boolean[3][5];
    cells[2][3] = true;

    int rows = Life2.numRows(cells);
    int cols = Life2.numCols(cells);

    System.out.println("number of rows = " + rows);
    System.out.println("number of columns = " + cols + "\n");

    System.out.println("is valid = " + Life2.isValid(cells, 0, 0));
    System.out.println("is valid = " + Life2.isValid(cells, rows, 0));
    System.out.println("is valid = " + Life2.isValid(cells, 0, cols) + "\n");

    boolean[][] copy = Life2.clone(cells);
    System.out.println("copy = " + Arrays.deepToString(copy) + "\n");

    Life2.printCells(cells);
    System.out.println("");

    boolean[][] neigh = Life2.neighborhood(cells, 1, 3);
    Life2.printCells(neigh);
    System.out.println("");

    boolean[][] neigh2 = Life2.neighborhood(cells, 2, 4);
    Life2.printCells(neigh2);
    System.out.println("");

    System.out.println("is alive = " + Life2.isAlive(cells, 0, 0));
    System.out.println("is alive = " + Life2.isAlive(cells, 2, 3) + "\n");

    boolean[][] cells2 = { { true, true }, { true, false } };
    Life2.printCells(cells2);
    System.out.println("num alive = " + Life2.numAlive(cells2));
  }
}
