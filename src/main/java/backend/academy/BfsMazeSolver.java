package backend.academy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BfsMazeSolver implements MazeSolver {
    private final Maze maze;
    private final Coordinate start;
    private final Coordinate finish;

    BfsMazeSolver(Maze maze, Coordinate start, Coordinate finish) {
        this.maze = new Maze(maze);
        this.start = new Coordinate(start);
        this.finish = new Coordinate(finish);
    }

    @Override
    public List<Coordinate> solveMaze() {
        Coordinate[][] grid = new Coordinate[maze.getWidth()][maze.getHeight()];
        for (int x = 0; x < maze.getWidth(); x++) {
            for (int y = 0; y < maze.getHeight(); y++) {
                grid[x][y] = new Coordinate(x, y);
                grid[x][y].setDistance(-1);
            }
        }
        grid[start.getX()][start.getY()].setDistance(0);
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(grid[start.getX()][start.getY()]);
        while (!queue.isEmpty()) {
            Coordinate currentCoordinate = queue.poll();
            if (currentCoordinate.getX() == finish.getX() && currentCoordinate.getY() == finish.getY()) {
                return getPath(grid);
            }
            List<Coordinate> eligibleNeighbours =
                getEligibleNeighbours(maze.getMazeGrid()[currentCoordinate.getX()][currentCoordinate.getY()]);
            for (Coordinate neighbour : eligibleNeighbours) {
                if (grid[neighbour.getX()][neighbour.getY()].getDistance() == -1) {
                    grid[neighbour.getX()][neighbour.getY()].setDistance(
                        grid[currentCoordinate.getX()][currentCoordinate.getY()].getDistance() + 1);
                    grid[neighbour.getX()][neighbour.getY()].setPreviousCoordinate(currentCoordinate);
                    queue.add(grid[neighbour.getX()][neighbour.getY()]);
                }
            }
        }
        return getPath(grid);
    }

    private List<Coordinate> getEligibleNeighbours(Cell cell) {
        List<Coordinate> eligibleNeighbours = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();
        for (Direction dir : Direction.values()) {
            if (!cell.hasWall(dir) && checkValidCell(x + dir.dx, y + dir.dy)) {
                eligibleNeighbours.add(new Coordinate(x + dir.dx, y + dir.dy));
            }
        }
        return eligibleNeighbours;
    }

    private boolean checkValidCell(int x, int y) {
        return x >= 0 && x < maze.getWidth() && y >= 0 && y < maze.getHeight();
    }

    private List<Coordinate> getPath(Coordinate[][] grid) {
        List<Coordinate> path = new ArrayList<>();
        path.add(grid[finish.getX()][finish.getY()]);
        Coordinate currentCoordinate = grid[finish.getX()][finish.getY()].getPreviousCoordinate();
        while (currentCoordinate.getX() != start.getX() || currentCoordinate.getY() != start.getY()) {
            path.add(currentCoordinate);
            currentCoordinate = grid[currentCoordinate.getX()][currentCoordinate.getY()].getPreviousCoordinate();
        }
        path.add(grid[start.getX()][start.getY()]);
        return path;
    }
}
