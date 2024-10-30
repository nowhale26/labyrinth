package backend.academy.maze;

import backend.academy.UserInteraction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InputTest {
    @Test
    public void checkStartParams() throws IOException {
        UserInteraction userInteraction = new UserInteraction();
        String simulatedInput =
            "0\n" +                      // Неверная высота
                "100\n" +                    // Неверная высота
                "f\n" +                        // Буква
                Constants.MIN_SIZE + "\n" +  // Верная высота
                "-1\n" +                     // Неверная ширина
                "100\n" +                    // Неверная ширина
                Constants.MIN_SIZE + "\n";   // Верная ширина
        String simulatedInput2 =
            "-1\n-1\n" +                 // Неверные координаты начальной позиции
                "a\n" +                      // Буква
                "0\n0\n" +                   // Верные координаты начальной позиции
                "-1\n-1\n" +                 // Неверные координаты конечной позиции
                "1\n1\n";                    // Верные координаты конечной позиции
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        userInteraction.getHeigthAndWidth(input, output);
        Assertions.assertThat(userInteraction.getHeight()).isEqualTo(Constants.MIN_SIZE);
        Assertions.assertThat(userInteraction.getWidth()).isEqualTo(Constants.MIN_SIZE);

        InputStream input2 = new ByteArrayInputStream(simulatedInput2.getBytes());

        userInteraction.getStartAndFinish(input2, output);

        Assertions.assertThat(userInteraction.getStart().getX()).isEqualTo(0);
        Assertions.assertThat(userInteraction.getStart().getY()).isEqualTo(0);

        Assertions.assertThat(userInteraction.getFinish().getX()).isEqualTo(1);
        Assertions.assertThat(userInteraction.getFinish().getY()).isEqualTo(1);
    }

    @Test
    public void checkChoice() throws IOException {
        UserInteraction userInteraction = new UserInteraction();
        String simulatedInput =
            "0\n" +                          // Неверная тип
                "50\n" +                     // Неверная тип
                "f\n" +                      // Буква
                "1\n";                       //Верный тип
        String simulatedInput2 =
            "-1\n" +                          // Неверная тип
                "1000\n" +                   // Неверная тип
                "п\n" +                      // Буква
                "2\n";                       //Верный тип
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        userInteraction.chooseMazeGenerator(input, output);
        Assertions.assertThat(userInteraction.getGeneratorType()).isEqualTo(1);

        InputStream input2 = new ByteArrayInputStream(simulatedInput2.getBytes());
        userInteraction.chooseMazeSolver(input2, output);
        Assertions.assertThat(userInteraction.getSolverType()).isEqualTo(2);
    }
}
