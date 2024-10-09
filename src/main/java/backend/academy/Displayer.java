package backend.academy;

public class Displayer implements MazeDisplayer {
    @Override
    public String displayMaze(Maze maze) {
        StringBuilder mazeString = new StringBuilder();
        final int width = maze.getWidth();
        final int height = maze.getHeight();
        final Cell[][] mazeGrid = maze.getMaze();
        for (int x = 0; x < width; x++) {
            mazeString.append("+");
            mazeString.append(mazeGrid[x][0].hasWall(Direction.NORTH) ? "---" : "   ");
        }
        mazeString.append("+\n");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mazeString.append(mazeGrid[x][y].hasWall(Direction.WEST) ? "|" : " ");
                mazeString.append("   ");
            }
            mazeString.append(mazeGrid[width - 1][y].hasWall(Direction.EAST) ? "|\n" : " \n");
            for (int x = 0; x < width; x++) {
                mazeString.append("+");
                mazeString.append(mazeGrid[x][y].hasWall(Direction.SOUTH) ? "---" : "   ");
            }
            mazeString.append("+\n");
        }
        return mazeString.toString();
    }
}
