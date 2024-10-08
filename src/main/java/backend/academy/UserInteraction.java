package backend.academy;

import lombok.Getter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

@Getter
public class UserInteraction {
    private int height;
    private int width;
    private Cell start;
    private Cell finish;

    public void getStartParams(InputStream input, OutputStream output) throws IOException {
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        writer.println("Введите высоту лабиринта от " + Constants.MIN_SIZE + " до " + Constants.MAX_SIZE);
        String heightInput = reader.readLine();
        height = Integer.parseInt(heightInput);

        writer.println("Введите ширину лабиринта от " + Constants.MIN_SIZE + " до " + Constants.MAX_SIZE);
        String widthInput = reader.readLine();
        width = Integer.parseInt(widthInput);

        writer.println("Введите позицию начала лабиринта");
        writer.println("Введите x координату от 0 до " + (height - 1));
        String xStartInput = reader.readLine();
        start.setX(Integer.parseInt(xStartInput));
        writer.println("Введите y координату от 0 до " + (width - 1));
        String yStartInput = reader.readLine();
        start.setY(Integer.parseInt(yStartInput));

        writer.println("Введите позицию конца лабиринта");
        writer.println("Введите x координату от 0 до " + (height - 1));
        String xFinishInput = reader.readLine();
        start.setX(Integer.parseInt(xFinishInput));
        writer.println("Введите y координату от 0 до " + (width - 1));
        String yFinishInput = reader.readLine();
        start.setY(Integer.parseInt(yFinishInput));
    }
}
