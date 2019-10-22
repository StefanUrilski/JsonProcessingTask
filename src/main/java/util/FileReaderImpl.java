package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderImpl implements FileReader {


    @Override
    public String readFile(String fileName) throws IOException {
        fileName = String.format("%s\\src\\main\\resources\\%s", System.getProperty("user.dir"), fileName);

        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            // or something else
            throw new IOException("Oops, something goes wrong... ");
        }

        return String.join(System.lineSeparator(), allLines);
    }
}
