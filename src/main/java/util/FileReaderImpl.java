package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReaderImpl implements FileReader {


    @Override
    public String readFile(String filePath) throws IOException {
        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            // or something else
            throw new IOException("Oops, something goes wrong... ");
        }

        return String.join(System.lineSeparator(), allLines);
    }
}
