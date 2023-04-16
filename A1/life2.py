'''
This module provides functions for the 2-dimensional Game of Life.

The 2-dimensional Game of Life occurs on an n-by-n array of
cells where each cell is either alive or dead. The population of cells
evolves from one generation to the next according to three rules:

1. Any live cell survives into the next generation if it has 2 or 3
living neighbours in its 1-neighborhood (the cell itself does not
count towards the number of living neighbors).
2. Any dead cell becomes alive in the next generation if it has 3
living neighbours in its 1-neighborhood.
3. All other live cells die, and all other dead cells remain dead.

The rules are applied to each cell simultaneously to compute the
next generation of cells.

The 1-neighborhood of a cell consists of the cell itself and its
eight neighbours, which are the cells that are horizontally, vertically,
or diagonally adjacent (if those neighbors exist).
'''


def num_rows(cells):
    '''
    Returns the number of rows in a two-dimensional list of cells.

    Parameters
    ---------
    cells : list-of-list of bool
        The cells of a 2D Game of Life.

    Returns
    -------
    bool
        The number or rows in `cells`.
    '''
    return len(cells)


def num_cols(cells):
    '''
    Returns the number of columns in a two-dimensional list of cells.

    Parameters
    ---------
    cells : list-of-list of bool
        The cells of a 2D Game of Life.

    Returns
    -------
    bool
        The number or columns in `cells`.
    '''
    if num_rows(cells) > 0:
        return len(cells[0])


def is_valid(cells, row, col):
    '''
    Returns True if row and col are valid indexes for the two-dimensional
    list of cells.

    Parameters
    ---------
    cells : list-of-list of bool
        The cells of a 2D Game of Life.
    row : int
        A row index.
    col : int
        A column index.

    Returns
    -------
    bool
        True if row and col are valid indexes for the two-dimensional
        list of cells.
    '''

    if row < 0 or row >= num_rows(cells):
        return False
    if col < 0 or col >= num_cols(cells):
        return False
    return True


def clone(cells):
    '''
    Returns a new two-dimensional list that is equal to the two-dimensional list
    `cells`.

    The returned list has the same number of rows and columns as `cells`
    and each element of the returned list is equal to the corresponding
    element in `cells`.

    Parameters
    ---------
    cells : list-of-list of bool
        The cells of a 2D Game of Life.

    Returns
    -------
    list
        A list-of-lists equal to `cells`
    '''
    rows = num_rows(cells)
    cols = num_cols(cells)
    # make a two-dimensional list having rows rows and cols columns
    # all values are set to False
    #
    # start with an empty list
    copy = []
    # make a list for each row of the original list
    for i in range(0, rows):
        # each row is made up of cols columns
        row = [False] * cols
        copy.append(row)

    # copy the values from cells into copy
    for r in range(0, rows):
        for c in range(0, cols):
            copy[r][c] = cells[r][c]
    return copy





def print_cells(cells):
    '''
    Prints the cells of a 2D Game of Life followed by a new line where the
    alive cells are represented as # and the dead cells are represented
    as - with no separator between cells. Each row of `cells` is printed
    on a separate line.

    Parameters
    ----------
    cells : list-of-list of bool
        The n-by-n cells of a 2D Game of Life.
    '''
    for row in cells:
        for c in row:
            if c:
                print('#', end = '')
            else:
                print('-', end = '')
        print()


    

def neighborhood(cells, row, col):
    '''
    Return the 1-neighborhood of cells around a specified cell.

    The 1-neighborhood of a cell consists of cells[row][col] and its
    eight neighbours, which are the cells that are horizontally, vertically,
    or diagonally adjacent (if those neighbors exist).

    Parameters
    ----------
    cells : list-of-list of bool
        The n-by-n cells of a 2D Game of Life.
    row : int
        The row index of the cell to get the neigborhood of.
    col : int
        The column index of the cell to get the neigborhood of.

    Returns
    -------
    list of bool
        A list containing the cells in the 1-neigborhood of cells
        around a specified cell.

    Raises
    ------
    ValueError
        If `row` or `col` is not a valid non-negative index for cells
    '''
    if not is_valid(cells, row, col):
        raise ValueError()

    # make a 3x3 to store the neighborhood
    nhood = [[False, False, False],
        [False, False, False],
        [False, False, False]]

    # upper-left indexes of the neighborhood
    # these might be out of bounds
    left = col - 1
    top = row - 1

    # get the neighborhood
    for r in range(0, 3):
        # row index for cells
        cellsRow = top + r
        for c in range(0, 3):
            # column index for cells
            cellsCol = left + c
            if is_valid(cells, cellsRow, cellsCol):
                nhood[r][c] = cells[cellsRow][cellsCol]
    return nhood


def is_alive(cells, row, col):
    '''
    Return True if a specified cell is alive.

    Returns True if cells[row][col] is True, and False otherwise.

    Parameters
    ----------
    cells: list-of-list of bool
        The cells of a 2D Game of Life.
    row : int
        The row index of the cell.
    col : int
        The column index of the cell.

    Returns
    -------
    bool
        True if cells[row][col] is True, and False otherwise.

    Raises
    ------
    ValueError
        If `row` or `col` is not a valid non-negative index for cells
    '''
    if not is_valid(cells, row, col):
        raise ValueError()

    return cells[row][col]


def num_alive(cells):
    '''
    Return number of alive cells.

    Unlike most other functions in this module, this function does not
    require a square array of cells. This function simply counts the
    number of alive cells in `cells`.

    Parameters
    ----------
    list-of-list of bool
        A two-dimensional array of cells.

    Returns
    -------
    int
        The number of alive cells in `cells`
    '''
    n_alive = 0
    for row in cells:
        for elem in row:
            if (elem):
                n_alive += 1
    return n_alive


