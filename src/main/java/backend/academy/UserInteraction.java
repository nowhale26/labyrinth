package backend.academy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.Getter;

@Getter
public class UserInteraction {
    private int height;
    private int width;
    private Coordinate start;
    private Coordinate finish;

    public void getStartParams(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        getHeigthAndWidth(writer, reader);
        getStartAndFinish(writer, reader);

    }

    public void drawSwampMaze(OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        writer.println("Болото обозначено символом '#'");
        MazeGenerator mazeGenerator = new PrimMazeGenerator(height, width);
        Maze maze = mazeGenerator.generateMaze();
        MazeSolver mazeSolver = new SwampMazeSolver(maze, start, finish);
        Displayer displayer = new Displayer();
        writer.println(displayer.displayMaze(maze));
        writer.println(displayer.displayMaze(maze, mazeSolver.solveMaze()));
    }

    public void drawMaze(OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        MazeGenerator mazeGenerator = new EllerMazeGenerator(height, width);
        Maze maze = mazeGenerator.generateMaze();
        MazeSolver mazeSolver = new BfsMazeSolver(maze, start, finish);
        Displayer displayer = new Displayer();
        writer.println(displayer.displayMaze(maze));
        writer.println(displayer.displayMaze(maze, mazeSolver.solveMaze()));
    }

    private void getHeigthAndWidth(PrintWriter writer, BufferedReader reader) throws IOException {
        height = 0;
        while (height < Constants.MIN_SIZE || height > Constants.MAX_SIZE) {
            writer.println("Введите высоту лабиринта от " + Constants.MIN_SIZE + Constants.BEFORE + Constants.MAX_SIZE);
            String heightInput = reader.readLine();

            if (heightInput == null) {
                writer.println("Ошибка: Не удалось прочитать высоту.");
                continue;
            }

            height = Integer.parseInt(heightInput);
        }

        width = 0;
        while (width < Constants.MIN_SIZE || width > Constants.MAX_SIZE) {
            writer.println("Введите ширину лабиринта от " + Constants.MIN_SIZE + Constants.BEFORE + Constants.MAX_SIZE);
            String widthInput = reader.readLine();

            if (widthInput == null) {
                writer.println("Ошибка: Не удалось прочитать ширину.");
                continue;
            }

            width = Integer.parseInt(widthInput);
        }
    }

    private void getStartAndFinish(PrintWriter writer, BufferedReader reader) throws IOException {
        start = new Coordinate(-1, -1);
        while (start.getX() > width - 1 || start.getY() > height - 1 || start.getX() < 0 || start.getY() < 0) {
            writer.println("Введите позицию начала лабиринта");
            writer.println(Constants.TYPE_X + (width - 1));

            String xStartInput = reader.readLine();
            if (xStartInput == null) {
                writer.println(Constants.ERROR_X);
                continue;
            }

            writer.println(Constants.TYPE_Y + (height - 1));
            String yStartInput = reader.readLine();
            if (yStartInput == null) {
                writer.println(Constants.ERROR_Y);
                continue;
            }

            start.setX(Integer.parseInt(xStartInput));
            start.setY(Integer.parseInt(yStartInput));
        }

        finish = new Coordinate(-1, -1);
        while (finish.getX() > width - 1 || finish.getY() > height - 1 || finish.getX() < 0 || finish.getY() < 0) {
            writer.println("Введите позицию конца лабиринта");
            writer.println(Constants.TYPE_X + (width - 1));

            String xFinishInput = reader.readLine();
            if (xFinishInput == null) {
                writer.println(Constants.ERROR_X);
                continue;
            }

            writer.println(Constants.TYPE_Y + (height - 1));
            String yFinishInput = reader.readLine();
            if (yFinishInput == null) {
                writer.println(Constants.ERROR_Y);
                continue;
            }

            finish.setX(Integer.parseInt(xFinishInput));
            finish.setY(Integer.parseInt(yFinishInput));
        }
    }
}
