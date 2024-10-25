package backend.academy;

import java.util.List;

public interface MazeDisplayer {
    String displayMaze(Maze maze);

    String displayMaze(Maze maze, List<Coordinate> path);
}
