package backend.academy;

import backend.academy.maze.Constants;
import backend.academy.maze.Coordinate;
import backend.academy.maze.Maze;
import backend.academy.mazedisplayers.Displayer;
import backend.academy.mazegenerators.EllerMazeGenerator;
import backend.academy.mazegenerators.MazeGenerator;
import backend.academy.mazegenerators.PrimMazeGenerator;
import backend.academy.mazesolvers.BfsMazeSolver;
import backend.academy.mazesolvers.MazeSolver;
import backend.academy.mazesolvers.SwampMazeSolver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class UserInteraction {
    private int height;
    private int width;
    private int generatorType = 0;
    private int solverType = 0;
    Maze maze;
    private Coordinate start;
    private Coordinate finish;

    private void drawPath(OutputStream output) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        writer.println("Болото обозначено символом '#'");
        MazeSolver mazeSolver;
        if (solverType == 1) {
            mazeSolver = new BfsMazeSolver(maze, start, finish);
        } else {
            mazeSolver = new SwampMazeSolver(maze, start, finish);
        }
        Displayer displayer = new Displayer();
        writer.println(displayer.displayMaze(maze, mazeSolver.solveMaze()));
    }

    private void drawMaze(OutputStream output) {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        MazeGenerator mazeGenerator;
        if (generatorType == 1) {
            mazeGenerator = new PrimMazeGenerator(height, width);
        } else {
            mazeGenerator = new EllerMazeGenerator(height, width);
        }
        maze = mazeGenerator.generateMaze();
        Displayer displayer = new Displayer();
        writer.println(displayer.displayMaze(maze));
    }

    public void getHeigthAndWidth(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        height = 0;
        while (height < Constants.MIN_SIZE || height > Constants.MAX_SIZE) {
            writer.println("Введите высоту лабиринта от " + Constants.MIN_SIZE + Constants.BEFORE + Constants.MAX_SIZE);
            String heightInput = reader.readLine();

            if (heightInput == null) {
                writer.println("Ошибка: Не удалось прочитать высоту.");
                continue;
            }

            if (!StringUtils.isNumeric(heightInput)) {
                writer.println("Ошибка: Высота должна быть числом.");
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

            if (!StringUtils.isNumeric(widthInput)) {
                writer.println("Ошибка: Ширина должна быть числом.");
                continue;
            }

            width = Integer.parseInt(widthInput);
        }
    }

    public void getStartAndFinish(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        getStartInput(writer, reader);
        getFinishInput(writer, reader);

    }

    public void startInteraction(InputStream input, OutputStream output) throws IOException {
        getHeigthAndWidth(input, output);
        chooseMazeGenerator(input, output);
        drawMaze(output);
        getStartAndFinish(input, output);
        chooseMazeSolver(input, output);
        drawPath(output);
    }

    public void chooseMazeGenerator(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        while (generatorType != 1 && generatorType != 2) {
            writer.println(
                "Введите 1, если хотите выбрать генерацию лабиринта алгоритмом Прима c болотами \n"
                    + "Введите 2, если если хотите выбрать генерацию лабиринта алгоритмом Эллера без болот");
            String generatorTypeString = reader.readLine();
            if (generatorTypeString == null) {
                writer.println("Ошибка: Не удалось прочитать тип генерации лабиринта");
                continue;
            }

            if (!StringUtils.isNumeric(generatorTypeString)) {
                writer.println("Ошибка: Тип генерации должен быть числом");
                continue;
            }

            generatorType = Integer.parseInt(generatorTypeString);
        }
    }

    public void chooseMazeSolver(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        while (solverType != 1 && solverType != 2) {
            writer.println(
                "Введите 1, если хотите выбрать поиск пути в лабиринте без учета болот\n"
                    + "Введите 2, если если хотите выбрать поиска пути в лабиринте с учетом болот");
            String solverTypeString = reader.readLine();
            if (solverTypeString == null) {
                writer.println("Ошибка: Не удалось прочитать тип поиска пути в лабиринте");
                continue;
            }

            if (!StringUtils.isNumeric(solverTypeString)) {
                writer.println("Ошибка: Тип поиска пути в лабиринте должен быть числом");
                continue;
            }

            solverType = Integer.parseInt(solverTypeString);
        }

    }

    private void getStartInput(PrintWriter writer, BufferedReader reader) throws IOException {
        start = new Coordinate(-1, -1);
        while (start.getX() > width - 1 || start.getY() > height - 1 || start.getX() < 0 || start.getY() < 0) {
            writer.println("Введите позицию начала лабиринта");
            writer.println(Constants.TYPE_X + (width - 1));

            String xStartInput = reader.readLine();
            if (xStartInput == null) {
                writer.println(Constants.ERROR_X);
                continue;
            }

            if (!StringUtils.isNumeric(xStartInput)) {
                writer.println(Constants.ERROR_COORDINATE);
                continue;
            }

            writer.println(Constants.TYPE_Y + (height - 1));
            String yStartInput = reader.readLine();
            if (yStartInput == null) {
                writer.println(Constants.ERROR_Y);
                continue;
            }

            if (!StringUtils.isNumeric(yStartInput)) {
                writer.println(Constants.ERROR_COORDINATE);
                continue;
            }

            start.setX(Integer.parseInt(xStartInput));
            start.setY(Integer.parseInt(yStartInput));
        }
    }

    private void getFinishInput(PrintWriter writer, BufferedReader reader) throws IOException {
        finish = new Coordinate(-1, -1);
        while (finish.getX() > width - 1 || finish.getY() > height - 1 || finish.getX() < 0 || finish.getY() < 0) {
            writer.println("Введите позицию конца лабиринта");
            writer.println(Constants.TYPE_X + (width - 1));

            String xFinishInput = reader.readLine();
            if (xFinishInput == null) {
                writer.println(Constants.ERROR_X);
                continue;
            }

            if (!StringUtils.isNumeric(xFinishInput)) {
                writer.println(Constants.ERROR_COORDINATE);
                continue;
            }

            writer.println(Constants.TYPE_Y + (height - 1));
            String yFinishInput = reader.readLine();
            if (yFinishInput == null) {
                writer.println(Constants.ERROR_Y);
                continue;
            }

            if (!StringUtils.isNumeric(yFinishInput)) {
                writer.println(Constants.ERROR_COORDINATE);
                continue;
            }

            finish.setX(Integer.parseInt(xFinishInput));
            finish.setY(Integer.parseInt(yFinishInput));
        }
    }
}
