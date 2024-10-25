package backend.academy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class SwampMazeSolver implements MazeSolver {
    private final Maze maze;
    private final Coordinate start;
    private final Coordinate finish;

    public SwampMazeSolver(Maze maze, Coordinate start, Coordinate finish) {
        this.maze = new Maze(maze);
        this.start = new Coordinate(start);
        this.finish = new Coordinate(finish);
    }

    @Override
    public List<Coordinate> solveMaze() {
        int[][] dist = new int[maze.getWidth()][maze.getHeight()];
        Cell[][] prev = new Cell[maze.getWidth()][maze.getHeight()];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dist[start.getX()][start.getY()] = 0;

        PriorityQueue<CellDistance> pq = new PriorityQueue<>();
        Cell startCell = maze.getMazeGrid()[start.getX()][start.getY()];
        Cell endCell = maze.getMazeGrid()[finish.getX()][finish.getY()];
        pq.add(new CellDistance(startCell, 0));

        while (!pq.isEmpty()) {
            CellDistance current = pq.poll();
            Cell currentCell = current.cell;
            int x = currentCell.getX();
            int y = currentCell.getY();

            if (current.distance > dist[x][y]) {
                continue;
            }

            if (currentCell.equals(endCell)) {
                break;
            }

            for (Direction dir : Direction.values()) {
                if (!currentCell.hasWall(dir)) {
                    int nx = x + dir.dx;
                    int ny = y + dir.dy;
                    if (checkValidCell(nx, ny)) {
                        Cell neighbor = maze.getMazeGrid()[nx][ny];
                        int weight = neighbor.isSwamp() ? Constants.SWAMP_VALUE : 1;
                        if (dist[nx][ny] > dist[x][y] + weight) {
                            dist[nx][ny] = dist[x][y] + weight;
                            prev[nx][ny] = currentCell;
                            pq.add(new CellDistance(neighbor, dist[nx][ny]));
                        }
                    }
                }
            }
        }

        List<Coordinate> path = new ArrayList<>();
        Cell cell = endCell;
        if (prev[cell.getX()][cell.getY()] != null || cell.getX() != start.getX() || cell.getY() != start.getY()) {
            while (cell != null) {
                path.add(new Coordinate(cell.getX(), cell.getY()));
                cell = prev[cell.getX()][cell.getY()];
            }
        } else {
            return null;
        }

        return path;

    }

    private boolean checkValidCell(int x, int y) {
        return x >= 0 && x < maze.getWidth() && y >= 0 && y < maze.getHeight();
    }

    private static class CellDistance implements Comparable<CellDistance> {
        Cell cell;
        int distance;

        CellDistance(Cell cell, int distance) {
            this.cell = cell;
            this.distance = distance;
        }

        @Override
        public int compareTo(CellDistance other) {
            return Integer.compare(this.distance, other.distance);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CellDistance other = (CellDistance) obj;
            return distance == other.distance && (cell != null ? cell.equals(other.cell) : other.cell == null);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cell, distance);
        }
    }
}
