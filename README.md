# ConwaysLife

Overview: 

This program is a graphical implementation of Conway's Game of Life, a cellular automaton designed by mathematician John Conway. The simulation runs on a grid where each cell can either be alive or dead. The program features an interactive interface and evolves the grid based on the rules of the game.

Features:

 - Interactive Grid (cells can be toggled between dead and alive)
 - Control Buttons (start, stop, step)
 - Visualization (Alive cells are displayed as blue, and grid is made to improve visualization)

Rules of the Game:

Underpopulation: A live cell with fewer than 2 live neighbors dies.
Overpopulation: A live cell with more than 3 live neighbors dies.
Reproduction: A dead cell with exactly 3 live neighbors becomes alive.
Stasis: A live cell with 2 or 3 live neighbors survives.

Technical Details
- Grid Representation: A 25x25 boolean array stores the states of all cells.
- GUI Framework: The program uses JFrame and JPanel for visualization.
- Graphics: The lifepanel class manages custom painting of the cells and grid lines.
- Threading: The life class uses a separate thread to run the simulation continuously.

