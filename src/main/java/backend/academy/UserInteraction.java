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

    public void getStartParams(InputStream input, OutputStream output) throws IOException{
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(output),true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

    }
}
