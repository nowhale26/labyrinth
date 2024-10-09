package backend.academy;

public class Maze {
    private final int height;
    private final int width;
    private Cell[][] maze;

    Maze(int height, int width, Cell[][] maze) {
        this.height = height;
        this.width = width;
        this.maze = maze;
    }
}
