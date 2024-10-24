package backend.academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimMazeGenerator implements MazeGenerator {
    private final int height;
    private final int width;
    private Cell[][] maze;
    private List<Edge> nextEdges = new ArrayList<>();
    private Random random = new Random();

    PrimMazeGenerator(int height, int width) {
        this.height = height;
        this.width = width;
        maze = new Cell[width][height];
        initializeMaze();
    }

    private void initializeMaze() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                maze[x][y] = new Cell(x, y);
            }
        }
    }

    @Override
    public Maze generateMaze() {
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        Cell startCell = maze[startX][startY];
        startCell.isVisited = true;
        addNextEdges(startCell);
        while (!nextEdges.isEmpty()) {
            Edge edge = nextEdges.remove(random.nextInt(nextEdges.size()));
            if (!edge.nextCell.isVisited) {
                edge.currentCell.removeWall(edge.nextCell);
                edge.nextCell.isVisited = true;
                addNextEdges(edge.nextCell);
            }
        }
        assignSwamp();
        return new Maze(height, width, maze);
    }

    private void assignSwamp() {
        int swamps = height * width / 5;
        while (swamps > 0) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            Cell cell = maze[x][y];
            if (!cell.isSwamp()) {
                cell.setSwamp(true);
                swamps--;
            }
        }
    }

    private void addNextEdges(Cell cell) {
        for (Direction dir : Direction.values()) {
            int dx = cell.getX() + dir.dx;
            int dy = cell.getY() + dir.dy;
            if (checkValidCell(dx, dy) && !maze[dx][dy].isVisited) {
                nextEdges.add(new Edge(cell, maze[dx][dy]));
            }
        }
    }

    private boolean checkValidCell(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
