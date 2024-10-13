package backend.academy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Displayer implements MazeDisplayer {
    @Override
    public String displayMaze(Maze maze) {
        StringBuilder mazeString = new StringBuilder();
        final int width = maze.getWidth();
        final int height = maze.getHeight();
        final Cell[][] mazeGrid = maze.getMazeGrid();
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

    @Override
    public String displayMaze(Maze maze, List<Coordinate> path) {
        StringBuilder mazeString = new StringBuilder();
        final int width = maze.getWidth();
        final int height = maze.getHeight();
        final Cell[][] mazeGrid = maze.getMazeGrid();

        Set<int[]> pathSet = new HashSet<>();
        for (Coordinate coord : path) {
            pathSet.add(new int[] {coord.getX(), coord.getY()});
        }

        for (int x = 0; x < width; x++) {
            mazeString.append("+");
            mazeString.append(mazeGrid[x][0].hasWall(Direction.NORTH) ? "---" : "   ");
        }
        mazeString.append("+\n");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                mazeString.append(mazeGrid[x][y].hasWall(Direction.WEST) ? "|" : " ");

                int finalX = x;
                int finalY = y;
                boolean isInPath = pathSet.stream().anyMatch(arr -> Arrays.equals(arr, new int[] {finalX, finalY}));

                if (isInPath) {
                    Coordinate start = path.getLast();
                    Coordinate finish = path.getFirst();
                    if (x == start.getX() && y == start.getY()) {
                        mazeString.append(" S ");
                    } else if (x == finish.getX() && y == finish.getY()) {
                        mazeString.append(" F ");
                    } else {
                        mazeString.append(" o ");
                    }
                } else {
                    mazeString.append("   ");
                }
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
