package backend.academy;

import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        UserInteraction interaction = new UserInteraction();
        interaction.getStartParams(System.in,System.out);
        interaction.drawSwampMaze(System.out);
    }
}
