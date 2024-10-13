package backend.academy;

import lombok.Getter;

@Getter
public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] maze;

    Maze(int height, int width, Cell[][] maze) {
        this.height = height;
        this.width = width;
        this.maze = new Cell[maze.length][maze[0].length];
        for(int i=0;i< maze.length;i++){
            for(int j=0;j<maze[0].length;j++){
                this.maze[i][j]= new Cell(maze[i][j]);
            }
        }
    }
}
