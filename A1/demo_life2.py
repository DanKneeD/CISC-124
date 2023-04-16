import life2

cells = [[False, False, False, False, False],
    [False, False, False, False, False],
    [False, False, False, True, False]]

rows = life2.num_rows(cells)
cols = life2.num_cols(cells)
print("number of rows =", rows)
print("number of columns =", cols)
print()

print("is valid =", life2.is_valid(cells, 0, 0))
print("is valid =", life2.is_valid(cells, rows, 0))
print("is valid =", life2.is_valid(cells, 0, cols))
print()

copy = life2.clone(cells)
print("copy =", copy)
print()

life2.print_cells(copy)
print()

neigh = life2.neighborhood(cells, 1, 3)
life2.print_cells(neigh)
print()

neigh = life2.neighborhood(cells, 2, 4)
life2.print_cells(neigh)
print()

print("is alive =", life2.is_alive(cells, 0, 0))
print("is alive =", life2.is_alive(cells, 2, 3))
print()

cells = [[True, True], [True, False]]
life2.print_cells(cells)
print("num alive =", life2.num_alive(cells))

