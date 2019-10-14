package util;

import java.io.IOException;

public class FileWriterImpl implements FileWriter {

    @Override
    public void write(String filePath, String text) throws IOException {

        java.io.FileWriter writer = new java.io.FileWriter(filePath);

        String[] data = text.split(System.lineSeparator());

        for (String dataLine : data) {
            writer.write(dataLine);
        }

        writer.close();
    }
}
