package backend.academy;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;

@Getter
public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] mazeGrid;

    @SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
    public Maze(int height, int width, Cell[][] mazeGrid) {
        this.height = height;
        this.width = width;
        this.mazeGrid = new Cell[mazeGrid.length][mazeGrid[0].length];
        for (int i = 0; i < mazeGrid.length; i++) {
            for (int j = 0; j < mazeGrid[0].length; j++) {
                this.mazeGrid[i][j] = new Cell(mazeGrid[i][j]);
            }
        }
    }

    @SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
    public Maze(Maze mazeGrid) {
        this.height = mazeGrid.getHeight();
        this.width = mazeGrid.getWidth();
        this.mazeGrid = new Cell[mazeGrid.getMazeGrid().length][mazeGrid.getMazeGrid()[0].length];
        for (int i = 0; i < mazeGrid.getMazeGrid().length; i++) {
            for (int j = 0; j < mazeGrid.getMazeGrid()[0].length; j++) {
                this.mazeGrid[i][j] = new Cell(mazeGrid.getMazeGrid()[i][j]);
            }
        }
    }
}
