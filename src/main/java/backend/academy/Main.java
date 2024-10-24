package backend.academy;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        PrimMazeGenerator mazeGenerator = new PrimMazeGenerator(5,5);
        Maze maze = mazeGenerator.generateMaze();
        SwampMazeSolver mazeSolver = new SwampMazeSolver(maze, new Coordinate(0,0), new Coordinate(4,3));
        Displayer displayer = new Displayer();
        System.out.println(displayer.displayMaze(maze));
        System.out.println(displayer.displayMaze(maze,mazeSolver.solveMaze()));
    }
}
