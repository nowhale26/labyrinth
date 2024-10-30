package backend.academy.mazedisplayers;

import backend.academy.maze.Coordinate;
import backend.academy.maze.Maze;
import java.util.List;

public interface MazeDisplayer {
    String displayMaze(Maze maze);

    String displayMaze(Maze maze, List<Coordinate> path);
}
