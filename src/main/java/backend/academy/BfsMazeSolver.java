package backend.academy;

import java.util.ArrayList;
import java.util.List;

public class BfsMazeSolver implements MazeSolver {
    private final Maze maze;
    private final Coordinate start;
    private final Coordinate finish;

    BfsMazeSolver(Maze maze, Coordinate start, Coordinate finish) {
        this.maze = maze;
        this.start = new Coordinate(start);
        this.finish = new Coordinate(finish);
    }

    @Override
    public List<Coordinate> solveMaze() {
        return List.of();
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
}
