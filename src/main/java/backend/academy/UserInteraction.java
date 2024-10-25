package backend.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import lombok.Getter;

@Getter
public class UserInteraction {
    private int height;
    private int width;
    private Coordinate start;
    private Coordinate finish;

    public void getStartParams(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        height = 0;
        while (height < Constants.MIN_SIZE || height > Constants.MAX_SIZE) {
            writer.println("Введите высоту лабиринта от " + Constants.MIN_SIZE + Constants.BEFORE + Constants.MAX_SIZE);
            String heightInput = reader.readLine();
            height = Integer.parseInt(heightInput);
        }
        width = 0;
        while (width < Constants.MIN_SIZE || width > Constants.MAX_SIZE) {
            writer.println("Введите ширину лабиринта от " + Constants.MIN_SIZE + Constants.BEFORE + Constants.MAX_SIZE);
            String widthInput = reader.readLine();
            width = Integer.parseInt(widthInput);
        }
        start = new Coordinate(-1, -1);
        while (start.getX() > width - 1 || start.getY() > height - 1 || start.getX() < 0 || start.getY() < 0) {
            writer.println("Введите позицию начала лабиринта");
            writer.println(Constants.TYPE_X + (width - 1));
            String xStartInput = reader.readLine();
            writer.println(Constants.TYPE_Y + (height - 1));
            String yStartInput = reader.readLine();
            start.setX(Integer.parseInt(xStartInput));
            start.setY(Integer.parseInt(yStartInput));
        }
        finish = new Coordinate(-1, -1);
        while (finish.getX() > width - 1 || finish.getY() > height - 1 || finish.getX() < 0 || finish.getY() < 0) {
            writer.println("Введите позицию конца лабиринта");
            writer.println(Constants.TYPE_X + (width - 1));
            String xFinishInput = reader.readLine();
            writer.println(Constants.TYPE_Y + (height - 1));
            String yFinishInput = reader.readLine();
            finish.setX(Integer.parseInt(xFinishInput));
            finish.setY(Integer.parseInt(yFinishInput));
        }

    }

    public void drawSwampMaze(OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        writer.println("Болото обозначено символом '#'");
        MazeGenerator mazeGenerator = new PrimMazeGenerator(height, width);
        Maze maze = mazeGenerator.generateMaze();
        MazeSolver mazeSolver = new SwampMazeSolver(maze, start, finish);
        Displayer displayer = new Displayer();
        writer.println(displayer.displayMaze(maze));
        writer.println(displayer.displayMaze(maze, mazeSolver.solveMaze()));
    }

    public void drawMaze(OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        MazeGenerator mazeGenerator = new EllerMazeGenerator(height, width);
        Maze maze = mazeGenerator.generateMaze();
        MazeSolver mazeSolver = new BfsMazeSolver(maze, start, finish);
        Displayer displayer = new Displayer();
        writer.println(displayer.displayMaze(maze));
        writer.println(displayer.displayMaze(maze, mazeSolver.solveMaze()));
    }
}
