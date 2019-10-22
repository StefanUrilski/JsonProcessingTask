package util;

import java.io.IOException;

public interface FileWriter {

    void write(String fileName, String text) throws IOException;
}
