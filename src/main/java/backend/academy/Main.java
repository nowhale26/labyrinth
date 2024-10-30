package backend.academy;

import java.io.IOException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws IOException {
        UserInteraction interaction = new UserInteraction();
        interaction.startInteraction(System.in, System.out);
    }
}
