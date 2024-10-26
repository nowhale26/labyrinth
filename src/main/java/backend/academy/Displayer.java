package backend.academy;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Displayer implements MazeDisplayer {
    @Override
    @SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
    public String displayMaze(Maze maze) {
        StringBuilder mazeString = new StringBuilder();
        final int width = maze.getWidth();
        final int height = maze.getHeight();
        final Cell[][] mazeGrid = maze.getMazeGrid();
        printFirstLine(mazeString, maze);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mazeString.append(mazeGrid[x][y].hasWall(Direction.WEST) ? "|" : " ");
                if (mazeGrid[x][y].isSwamp()) {
                    mazeString.append(Constants.SWAMP_SYMBOL);
                } else {
                    mazeString.append(Constants.SPACE);
                }
            }
            mazeString.append(
                mazeGrid[width - 1][y].hasWall(Direction.EAST) ? Constants.WALL_LINE_BREAKER : Constants.LINE_BREAKER);
            for (int x = 0; x < width; x++) {
                mazeString.append('+');
                mazeString.append(
                    mazeGrid[x][y].hasWall(Direction.SOUTH) ? Constants.HORIZONTAL_WALL : Constants.SPACE);
            }
            mazeString.append(Constants.CROSS_LINE_BREAKER);
        }
        return mazeString.toString();
    }

    @Override
    @SuppressFBWarnings("CLI_CONSTANT_LIST_INDEX")
    public String displayMaze(Maze maze, List<Coordinate> path) {
        StringBuilder mazeString = new StringBuilder();
        final int width = maze.getWidth();
        final int height = maze.getHeight();
        final Cell[][] mazeGrid = maze.getMazeGrid();

        Set<Coordinate> pathSet = new HashSet<>(path);

        printFirstLine(mazeString, maze);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mazeString.append(mazeGrid[x][y].hasWall(Direction.WEST) ? "|" : " ");

                Coordinate currentCoord = new Coordinate(x, y);
                boolean isInPath = pathSet.contains(currentCoord);

                if (isInPath) {
                    Coordinate start = path.get(path.size() - 1);
                    Coordinate finish = path.get(0);
                    if (x == start.getX() && y == start.getY()) {
                        mazeString.append(" S ");
                    } else if (x == finish.getX() && y == finish.getY()) {
                        mazeString.append(" F ");
                    } else {
                        mazeString.append(" o ");
                    }
                } else {
                    if (mazeGrid[x][y].isSwamp()) {
                        mazeString.append(Constants.SWAMP_SYMBOL);
                    } else {
                        mazeString.append(Constants.SPACE);
                    }
                }
            }

            mazeString.append(
                mazeGrid[width - 1][y].hasWall(Direction.EAST) ? Constants.WALL_LINE_BREAKER : Constants.LINE_BREAKER);

            for (int x = 0; x < width; x++) {
                mazeString.append('+');
                mazeString.append(
                    mazeGrid[x][y].hasWall(Direction.SOUTH) ? Constants.HORIZONTAL_WALL : Constants.SPACE);
            }
            mazeString.append(Constants.CROSS_LINE_BREAKER);
        }

        return mazeString.toString();
    }

    private void printFirstLine(StringBuilder mazeString, Maze maze) {
        for (int x = 0; x < maze.getWidth(); x++) {
            mazeString.append('+');
            mazeString.append(
                maze.getMazeGrid()[x][0].hasWall(Direction.NORTH) ? Constants.HORIZONTAL_WALL : Constants.SPACE);
        }
        mazeString.append(Constants.CROSS_LINE_BREAKER);
    }
}
