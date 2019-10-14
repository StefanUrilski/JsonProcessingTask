package util;

import java.io.IOException;

public interface FileWriter {

    void write(String filePath, String text) throws IOException;
}
