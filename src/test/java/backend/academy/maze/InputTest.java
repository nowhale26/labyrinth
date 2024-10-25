package backend.academy.maze;

import backend.academy.Constants;
import backend.academy.UserInteraction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class InputTest {
    @Test
    public void checkInput() throws IOException {
        UserInteraction userInteraction = new UserInteraction();
        String simulatedInput =
            "0\n" +                      // Неверная высота
            "100\n" +                    // Неверная высота
            Constants.MIN_SIZE + "\n" +  // Верная высота
            "-1\n" +                     // Неверная ширина
            "100\n" +                    // Неверная ширина
            Constants.MIN_SIZE + "\n" +  // Верная ширина
            "-1\n-1\n" +                 // Неверные координаты начальной позиции
            "0\n0\n" +                   // Верные координаты начальной позиции
            "-1\n-1\n" +                 // Неверные координаты конечной позиции
            "1\n1\n";                    // Верные координаты конечной позиции
        InputStream input = new ByteArrayInputStream(simulatedInput.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        userInteraction.getStartParams(input,output);
        Assertions.assertThat(userInteraction.getHeight()).isEqualTo(Constants.MIN_SIZE);
        Assertions.assertThat(userInteraction.getWidth()).isEqualTo(Constants.MIN_SIZE);

        Assertions.assertThat(userInteraction.getStart().getX()).isEqualTo(0);
        Assertions.assertThat(userInteraction.getStart().getY()).isEqualTo(0);

        Assertions.assertThat(userInteraction.getFinish().getX()).isEqualTo(1);
        Assertions.assertThat(userInteraction.getFinish().getY()).isEqualTo(1);
    }
}
