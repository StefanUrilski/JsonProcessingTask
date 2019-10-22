package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static common.Constants.INVALID_READ_WRITE_FILE_NAME_MESSAGE;

public class FileReaderImpl implements FileReader {


    @Override
    public String readFile(String fileName) throws IOException {
        String filePath = String.format("%s\\src\\main\\resources\\%s", System.getProperty("user.dir"), fileName);

        List<String> allLines;
        try {
            allLines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            // or something else
            throw new IOException(INVALID_READ_WRITE_FILE_NAME_MESSAGE);
        }

        return String.join(System.lineSeparator(), allLines);
    }
}
