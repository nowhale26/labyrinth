package backend.academy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EllerMazeGenerator implements MazeGenerator {
    private final int height;
    private final int width;
    private Cell[][] maze;
    private Random random = new Random();
    private int[] line;
    private int nextGroup;

    EllerMazeGenerator(int height, int width) {
        this.height = height;
        this.width = width;
        maze = new Cell[width][height];
        initializeMaze();
        nextGroup = width;
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
        line = new int[width];

        // Инициализация первой строки
        for (int x = 0; x < width; x++) {
            line[x] = x;
        }

        for (int y = 0; y < height; y++) {
            // Обрабатываем горизонтальные стены
            for (int x = 0; x < width - 1; x++) {
                placeHorizontalWalls(x, y);
            }

            // Обрабатываем вертикальные стены (кроме последней строки)
            if (y < height - 1) {
                placeVerticalWalls(y);
            } else {
                // Обработка последней строки
                for (int x = 0; x < width - 1; x++) {
                    if (line[x] != line[x + 1]) {
                        maze[x][y].removeWall(maze[x + 1][y]);
                        mergeGroups(line[x + 1], line[x]);
                    }
                }
            }

            // Копируем номера групп на следующую строку
            if (y < height - 1) {
                copyPreviousLine(y + 1);
            }
        }

        return new Maze(height, width, maze);
    }

    private void copyPreviousLine(int y) {
        for (int x = 0; x < width; x++) {
            maze[x][y] = new Cell(x, y);
            if (!maze[x][y - 1].hasWall(Direction.SOUTH)) {
                line[x] = nextGroup++;
            }
        }
    }

    private void placeHorizontalWalls(int x, int y) {
        if (line[x] == line[x + 1]) {
            // Если ячейки в одной группе, ставим стену между ними
            maze[x][y].placeWall(maze[x + 1][y]);
        } else if (random.nextBoolean()) {
            // Случайно решаем поставить стену
            maze[x][y].placeWall(maze[x + 1][y]);
        } else {
            // Удаляем стену и объединяем группы
            maze[x][y].removeWall(maze[x + 1][y]);
            mergeGroups(line[x + 1], line[x]);
        }
    }

    private void placeVerticalWalls(int y) {
        Map<Integer, List<Integer>> groupCells = new HashMap<>();
        for (int x = 0; x < width; x++) {
            groupCells.computeIfAbsent(line[x], k -> new ArrayList<>()).add(x);
        }

        for (Map.Entry<Integer, List<Integer>> entry : groupCells.entrySet()) {
            List<Integer> cells = entry.getValue();
            int connections = random.nextInt(cells.size()) + 1;
            Collections.shuffle(cells);
            for (int i = 0; i < cells.size(); i++) {
                int x = cells.get(i);
                if (i < connections) {
                    // Удаляем стену вниз
                    maze[x][y].removeWall(maze[x][y + 1]);
                } else {
                    // Ставим стену вниз
                    maze[x][y].placeWall(maze[x][y + 1]);
                }
            }
        }
    }

    private void mergeGroups(int oldGroup, int newGroup) {
        for (int i = 0; i < width; i++) {
            if (line[i] == oldGroup) {
                line[i] = newGroup;
            }
        }
    }
}

